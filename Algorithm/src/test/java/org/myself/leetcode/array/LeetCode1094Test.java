package org.myself.leetcode.array;

import org.junit.Test;

public class LeetCode1094Test {

    @Test
    public void test() {
        int[][] trips = {
                {2, 1, 5},
                {3, 5, 7}
        };

        LeetCode1094 solution = new LeetCode1094();
        solution.carPooling(trips, 3);
    }
}
