package org.myself.leetcode.array;

/**
 * 车上最初有capacity个空座位。车只能向一个方向行驶（也就是说，不允许掉头或改变方向）
 *
 * 给定整数capacity和一个数组 trips , trip[i] = [numPassengersi, fromi, toi]
 * 表示第 i 次旅行有numPassengersi乘客，接他们和放他们的位置分别是fromi和toi。这些位置是从汽车的初始位置向东的公里数。
 *
 * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回true，否则请返回 false。
 * @author mapengfei
 */
public class LeetCode1094 {
    private int[] diff;

    public boolean carPooling(int[][] trips, int capacity) {
        int length = trips.length;
        diff = new int[trips[length - 1][2]];

        for(int i = 0; i < length; i++) {
            int   val = trips[i][0];
            int  left = trips[i][1];
            int right = trips[i][2];
            increment(left - 1, right - 1, val);
        }

        return result(capacity);
    }

    public void increment(int i, int j, int val) {
        diff[i] += val;

        if (j + 1 < diff.length) {
            diff[j + 1] -= val;
        }
    }

    public boolean result(int capacity) {
        int[] result = new int[diff.length];
        result[0] = diff[0];

        for(int i = 1; i < result.length; i++) {
            result[i] = result[i-1] + diff[i];
            if (result[i] > capacity) {
                return false;
            }
        }

        return true;
    }
}
