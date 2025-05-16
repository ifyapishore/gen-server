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
        if(k >= arr.length) {
            long sum = 0;
            for (int i = 0; i < arr.length; i++) {
                sum += arr[i];
            }
            return sum;
        }
        long max = Integer.MIN_VALUE;
        for (int start = 0; start <= arr.length - k; start++) {
            for (int end = start + 1; end < start + k + 1; end++) {
                int sum = 0;
                for (int i = start; i < end; i++) {
                    sum += arr[i];
                }
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    /**
     * This method calculates the maximum sum of a contiguous subarray of a given size.
     *
     * @param arr          The input array of integers. Values can be negative.
     * @param maxSliceSize The maximum size of the subarray.
     * @return The maximum sum of the contiguous subarray.
     */
    public static int maxSumOfRange(int[] arr, int maxSliceSize) {
        final int k = maxSliceSize;
        final int[] a = arr;
        if (boundaryFail(a, k)) {
            return 0; // or throw an exception
        }

        if (k == 1) {
            int max = Integer.MIN_VALUE;
            // TODO: Check SIMD optimization of your JVM (20+)
            for (int i = 0; i < arr.length; i++) {
                final int v = arr[i];
//                max = (v > max) ? v : max;
                max = Math.max(max, v);
            }
            return max;
        }
        // SPECIAL CASE: compact data (maxSliceSize & arr.length < than COU cores)
        // TODO: Check the JVM insights before using this code by the factory method

        // STEP 1: Detect min values of the array - c*(N)
        // TODO: Check the compiler capabilities to optimize this code using SIMD instructions.
        int maxArrayCellValue = Integer.MAX_VALUE;
        boolean hasNegatives = false;

        for (int i = 0; i < arr.length; i++) {
            var cellValue = arr[i];
            hasNegatives |= cellValue < 0;
            //CATCH: Math.min can't be transponded to other lang without using a library
            maxArrayCellValue = Math.min(maxArrayCellValue, cellValue);
        }

        return hasNegatives ?
                maxSumOfRangeOfSemiPositives(arr, maxSliceSize) :
                maxSumOfRangeOfNegatives(arr, maxSliceSize);
    }

    public static int maxSumOfRangeOfNegatives(int[] arr, int maxSliceSize) {
        // we must respect the presence of negative numbers in the arrays.
        // 1) Use complicated math to calculate the max value of the array
        // 2) Rely on number boundary overlow and use bitwise masks
        return 0;
    }

    public static int maxSumOfRangeOfSemiPositives(int[] arr, int maxSliceSize) {
        // All array items are positive or zero.
        // Sum of every subarray of size maxSliceSize is bigger ot equal to (maxSliceSize-1)
        int max = 0;// all numbers are positive
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (i >= maxSliceSize) {
                sum -= arr[i - maxSliceSize];
            }
            if (sum > max) {
                max = sum;
            }
        }

        return max;
    }

}
