package org.myself.leetcode.array;

import org.junit.Test;

/**
 * [[1,2,10],[2,3,20],[2,5,25]]
 * 5
 */
public class LeetCode1109Test {
    @Test
    public void test() {
        int[][] bookings = {
                {1, 2, 10},
                {2, 3, 20},
                {2, 5, 25}
        };

        LeetCode1109 solution = new LeetCode1109();
        solution.corpFlightBookings(bookings, 5);
    }
}
