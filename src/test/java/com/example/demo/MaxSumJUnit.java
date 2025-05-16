package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxSumJUnit {

    private void srun(int expected, int K, int... arr) {
        long etalong = MaxSum.maxSumOfRange_Primitive(arr, K);
        long res = MaxSum.maxSumOfRange(arr, K);
        assertEquals(expected, etalong);
//        assertEquals(expected, res);
    }
    @Test
    public void test() {
        // preconditions check
        this.srun(0, 0, null);
        this.srun(0, -1, new int[]{});
        this.srun(0, 0, new int[]{1});
        this.srun(0, -1, new int[]{1});

        // K > length
        this.srun(-2, 20, new int[]{-1, -1});

        // test with negative values
        this.srun(1, 1, new int[]{1});
        this.srun(-1, 1, new int[]{-1, -1});
        this.srun(-2, 2, new int[]{-1, -1});
    }
}
