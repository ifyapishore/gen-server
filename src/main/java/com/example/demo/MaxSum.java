package com.example.demo;

public class MaxSum {

    public static boolean boundaryFail(int[] arr, int maxSliceSize) {
        return arr == null || arr.length == 0 || maxSliceSize <= 0;
    }

    public static long maxSumOfRange_Primitive(int[] arr, int maxSliceSize) {
        final int k = maxSliceSize;
        final int[] a = arr;
        if (boundaryFail(a, k)) {
            return 0; // or throw an exception
        }
        long max = Integer.MIN_VALUE;
        for (int start = 0; start <= a.length - 1; start++) {
            var arrEnd = Math.min(arr.length, start + k) + 1;
            for (int end = start + 1; end < arrEnd; end++) {
                int sum = 0;
                int maxEnd = Math.min(end, a.length);
                for (int i = start; i < maxEnd; i++) {
                    sum += a[i];
                }
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    public static long maxSumOfRange_Optimized(int[] arr, int maxSliceSize) {
        final int k = maxSliceSize;
        final int[] a = arr;
        if (boundaryFail(a, k)) {
            return 0; // or throw an exception
        }
        // determnte the positive only case and min max values
        int sv_min = Integer.MAX_VALUE;
        int sv_max = Integer.MIN_VALUE;
        boolean has_negatives = true;
        for (int i = 0; i < a.length; i++) {
            var v = a[i];
            if (v < sv_min) sv_min = v;
            if (v > sv_max) sv_max = v;
            has_negatives |= v < 0;
        }
        if (k == 1) {
            // if k == 1, we can just return the max value
            return sv_max;
        }

        if (has_negatives) {
            long max = Integer.MIN_VALUE;
            for (int start = 0; start <= a.length - 1; start++) {
                for (int end = start + 1; end < start + k + 1; end++) {
                    int sum = 0;
                    int maxEnd = Math.min(end, a.length);
                    for (int i = start; i < maxEnd; i++) {
                        sum += a[i];
                    }
                    max = Math.max(max, sum);
                }
            }
            return max;
        } else {
            // all positive, we can assume that max window size will give the max
            long max = Integer.MIN_VALUE;
            for (int start = 0; start <= a.length - 1; start++) {
                int sum = 0;
                int maxEnd = Math.min(start + k, a.length);
                for (int i = start; i < maxEnd; i++) {
                    sum += a[i];
                }
                max = Math.max(max, sum);
            }
            return max;
        }
    }

    public static long maxSumOfRange_Optimized2(int[] arr, int maxSliceSize) {
        final int k = maxSliceSize;
        final int[] a = arr;
        if (boundaryFail(a, k)) {
            return 0; // or throw an exception
        }
        // Detect the positive only case and min max values
        int sv_min = Integer.MAX_VALUE;
        int sv_max = Integer.MIN_VALUE;
        boolean has_negatives = true;

        // O(n)
        for (int i = 0; i < a.length; i++) {
            var v = a[i];
            if (v < sv_min) sv_min = v;
            if (v > sv_max) sv_max = v;
            has_negatives |= v < 0;
        }

        if (has_negatives) {
            // O(n) * O(k) * O(k)
            long max = Integer.MIN_VALUE;
            for (int start = 0; start <= a.length - 1; start++) {
                for (int end = start + 1; end < start + k + 1; end++) {
                    int sum = 0;
                    int maxEnd = Math.min(end, a.length);
                    for (int i = start; i < maxEnd; i++) {
                        sum += a[i];
                    }
                    max = Math.max(max, sum);
                }
            }
            return max;
        } else {
            // O(n) + O(k)
            // all positive, we can assume that max window size will give the max sum
            // apply running window

            // first window sum
            long max = 0;
            for (int i = 0; i < Math.min(k, a.length); i++) {
                max += a[i];
            }

            // running window, subtract tail and add head.
            int lastIndex = a.length - k;
            for (int start = 1; start <= lastIndex; start++) {
                int tail = a[start - 1];
                int head = a[start + k];
                long val = max - tail + head;
                max = val > max ? val : max;
            }
            return max;
        }
    }


    public static long maxSumOfRange_Optimized3(int[] arr, int maxSliceSize) {
        final int k = maxSliceSize;
        final int[] a = arr;
        if (boundaryFail(a, k)) {
            return 0; // or throw an exception
        }
        // Detect the positive only case and min max values
        int sv_min = Integer.MAX_VALUE;
        int sv_max = Integer.MIN_VALUE;
        boolean has_negatives = true;

        // O(n)
        for (int i = 0; i < a.length; i++) {
            var v = a[i];
            if (v < sv_min) sv_min = v;
            if (v > sv_max) sv_max = v;
            has_negatives |= v < 0;
        }

        if (has_negatives) {
            // O(n) * O(k) * O(k)
            long max = Integer.MIN_VALUE;
            for (int start = 0; start <= a.length - 1; start++) {
                for (int end = start + 1; end < start + k + 1; end++) {
                    int sum = 0;
                    int maxEnd = Math.min(end, a.length);
                    for (int i = start; i < maxEnd; i++) {
                        sum += a[i];
                    }
                    max = Math.max(max, sum);
                }
            }
            return max;
        } else {
            // O(n) + O(k)
            // all positive, we can assume that max window size will give the max sum
            // apply running window

            // first window sum
            long max = 0;
            for (int i = 0; i < Math.min(k, a.length); i++) {
                max += a[i];
            }

            // running window, subtract tail and add head.
            int lastIndex = a.length - k;
            for (int start = 1; start <= lastIndex; start++) {
                int tail = a[start - 1];
                int head = a[start + k];
                long val = max - tail + head;
                max = val > max ? val : max;
            }
            return max;
        }
    }


    public static long maxSumOfRange_Optimized4(int[] arr, int maxSliceSize) {
        //TODO:
        // 1. remove last loop iteration
        // 2. Use UPPER + long for computation
        // 3. Use a single running window loop for both pure positive and pure negative arrays
        // 4. Fix loops to force JVM to use SIMD
        // CONTENT DEPENDED OPTIMIZATIONS:
        // 1. For mixed content calculate threshold and if it is optimal - use slices calculation for positive/negative sequences
        // 2. For very long arrays: split into positive/negative arrays, find max separately, and merge.
        //    Can be slower when O(n) * O(k) * O(k) highly depends on content and may consume a memory for temporary arrays.
        // 3. For low K values add special processing (store separate running window in local vars)
        // 4. For the big K value, add temporary array for running window for each k.
        //    Reduce complexity to O(n) * O(k) for most of cases.

        return 0;
    }

}
