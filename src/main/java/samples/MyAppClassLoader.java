package samples;

import java.io.*;
import java.util.Map;
import java.util.concurrent.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class MyAppClassLoader extends ClassLoader {

    private final Map<String, byte[]> classBytes = new ConcurrentHashMap<>();
    private final File zipFile;
    private final ExecutorService loaderThread = Executors.newSingleThreadExecutor();
    private final CountDownLatch loadingDone = new CountDownLatch(1);

    public MyAppClassLoader(File zipFile) {
        super(MyAppClassLoader.class.getClassLoader()); // delegate to system classloader
        this.zipFile = zipFile;
        startLoader();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (!name.startsWith("myapp.")) {
            throw new ClassNotFoundException(name);
        }

        // Convert class name to path
        String entryPath = name.replace('.', '/') + ".class";

        try {
            // Wait until all classes are loaded or class appears in map
            while (!classBytes.containsKey(name)) {
                if (loadingDone.await(10, TimeUnit.MILLISECONDS)) break;
            }

            byte[] bytes = classBytes.remove(name);
            if (bytes == null) throw new ClassNotFoundException(name);
            return defineClass(name, bytes, 0, bytes.length);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ClassNotFoundException(name, e);
        }
    }

    private void startLoader() {
        loaderThread.submit(() -> {
            try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFile))) {
                ZipEntry entry;
                while ((entry = zin.getNextEntry()) != null) {
                    if (entry.isDirectory() || !entry.getName().endsWith(".class")) continue;

                    byte[] buf = zin.readAllBytes();
                    String className = entry.getName()
                            .replace('/', '.')
                            .replace(".class", "");

                    if (className.startsWith("myapp.")) {
                        classBytes.put(className, buf);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                loadingDone.countDown();
                loaderThread.shutdown();
            }
        });
    }
}
