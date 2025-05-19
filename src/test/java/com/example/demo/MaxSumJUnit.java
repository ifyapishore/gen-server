package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

interface PerfRun {
    void run(int[] arr, int k);
}

public class MaxSumJUnit {

    private void srun(int expected, int K, int... arr) {
        assertEquals(expected, MaxSum.maxSumOfRange_Primitive(arr, K), "k=" + K + " arr=" + Arrays.toString(arr));
        assertEquals(expected, MaxSum.maxSumOfRange_Optimized(arr, K), "k=" + K + " arr=" + Arrays.toString(arr));
        assertEquals(expected, MaxSum.maxSumOfRange_Optimized2(arr, K), "k=" + K + " arr=" + Arrays.toString(arr));
        assertEquals(expected, MaxSum.maxSumOfRange_Optimized3(arr, K), "k=" + K + " arr=" + Arrays.toString(arr));
    }

    @Test
    public void testPerformance() {
        int[] arr = new int[1000];
        for (int j = 0; j < arr.length; j++) {
            arr[j] = (int) (Math.random() * 1000);
        }

        testPerformanceLoop(new PerfRun() {
            @Override
            public void run(int[] arr, int k) {
                MaxSum.maxSumOfRange_Primitive(arr, k);
            }
        }, arr,"Primitive");
        testPerformanceLoop(new PerfRun() {
            @Override
            public void run(int[] arr, int k) {
                MaxSum.maxSumOfRange_Optimized(arr, k);
            }
        },arr, "Optimized");
        testPerformanceLoop(new PerfRun() {
            @Override
            public void run(int[] arr, int k) {
                MaxSum.maxSumOfRange_Optimized2(arr, k);
            }
        }, arr,"Optimized2");
        testPerformanceLoop(new PerfRun() {
            @Override
            public void run(int[] arr, int k) {
                MaxSum.maxSumOfRange_Optimized3(arr, k);
            }
        },arr, "Optimized3");
    }

    private void testPerformanceLoop(PerfRun func, int[] arr, String name) {
        long ts = - System.currentTimeMillis();
        for(int i=0; i < 10_000; i++) {
            func.run(arr, 10);
        }
        ts += System.currentTimeMillis();

        System.out.println(name + " took " + ts + " ms");
    }

    @Test
    public void test() {
        // danger zone
        this.srun(2, 2, new int[]{-1, 2});
        this.srun(2, 2, new int[]{-1, 2, -100, -200});
        this.srun(2, 1, new int[]{-1, 2, -100, -200});
        this.srun(0, 0, new int[]{-2, 2});
        for (int k = 1; k < 10; k++) {
            this.srun(2, k, new int[]{-2, 2, -123, -123});
        }
        for (int k = 1; k < 10; k++) {
            this.srun(2, k, new int[]{2});
        }
        for (int k = -100; k <= 0; k++) {
            this.srun(0, k, new int[]{2});
        }
        this.srun(2, 2, new int[]{-1, 1, -2, 2});

        // preconditions check
        this.srun(0, 0, null);
        this.srun(0, -1, new int[]{});
        this.srun(0, 0, new int[]{1});
        this.srun(0, -1, new int[]{1});

        // K > length
        this.srun(-1, 20, new int[]{-1, -1});

        // test with negative values
        this.srun(1, 1, new int[]{1});
        this.srun(-1, 1, new int[]{-1, -1});
        this.srun(-1, 2, new int[]{-1, -1});
        // mixed
        this.srun(2, 20, new int[]{-1, -1, 2});
        this.srun(3, 20, new int[]{-1, -1, 2, 1});
        this.srun(0, 0, new int[]{-1, -1, 2});
        // k limits
        this.srun(2, 2, new int[]{-1, 1, -2, 2});
        this.srun(2, 3, new int[]{-1, 1, -2, 2});

        // text positives
        this.srun(5, 3, new int[]{1, 1, 2, 2});
        this.srun(14, 4, new int[]{1, 1, 2, 2, 3, 4, 5});
        this.srun(9, 3, new int[]{1, 1, 2, 2, 3, 4});
        System.out.println("\uD83D\uDC9A".repeat(20) + "\n Passed.");
    }
}
