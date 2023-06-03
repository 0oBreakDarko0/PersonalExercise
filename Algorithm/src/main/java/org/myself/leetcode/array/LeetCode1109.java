package org.myself.leetcode.array;

/**
 * 这里有n个航班，它们分别从 1 到 n 进行编号。
 *
 * 有一份航班预订表bookings ，表中第i条预订记录bookings[i] = [firsti, lasti, seatsi]
 * 意味着在从 firsti到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi个座位。
 *
 * 请你返回一个长度为 n 的数组answer，里面的元素是每个航班预定的座位总数。

 * @author mapengfei
 */
public class LeetCode1109 {

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
            diff[j + 1] -= val;
        }
    }

    public int[] result() {
        int[] result = new int[diff.length];

        result[0] = diff[0];
        for(int i = 1; i < diff.length; i++) {
            result[i] = result[i-1] + diff[i];
        }

        return result;
    }
}
