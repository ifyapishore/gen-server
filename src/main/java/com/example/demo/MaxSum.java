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
        if(k == 1) {
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
            // all positive, we can assume that max window size will give the max sum
            // apply running window

            // first window sum
            long max = 0;
            for (int i = 0; i < Math.min(k, a.length); i++) {
                max += a[i];
            }

            // running window, subtract tail and add head.
            // until the last shrinking window
            int lastIndex = a.length - k;
            for (int start = 1; start <= lastIndex; start++) {
                int tail = a[start - 1];
                int head = a[start + k];
                long val = max - tail + head;
                max = val > max ? val : max;
            }
            // last window
            int lastWindowSum = 0;
            for (int i = lastIndex + 1; i < a.length; i++) {
                lastWindowSum += a[i];
            }
            return lastWindowSum > max ? lastWindowSum : max;
        }
    }
}
