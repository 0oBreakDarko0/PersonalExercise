package org.myself;

import org.myself.leetcode.listnode.ListNode;

class Solution {
    private int[] diff;

    public int[] corpFlightBookings(int[][] bookings, int n) {
        diff = new int[n];

        for (int i = 0; i < bookings.length; i++) {
            int first = bookings[i][0];
            int  last = bookings[i][1];
            int   val = bookings[i][2];

            increment(first - 1, last - 1, val);
        }

        return result();
    }

    public void increment(int i, int j, int val) {
        diff[i] += val;
        if(j + 1 < diff.length) {
            diff[j + 1] -= 3;
        }
    }

    public int[] result() {
        int[] result = new int[diff.length];

        for(int i = 1; i < diff.length; i++) {
            result[i] = result[i-1] + diff[i];
        }

        return result;
    }
}
