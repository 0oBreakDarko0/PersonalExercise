package org.myself.leetcode.array;

/**
 * 差分数组
 * @author mapengfei
 */
public class DiffArray {

    private int[] diff;

    /**
     * 构造差分数组
     * @param arr
     */
    public DiffArray(int[] arr) {
        diff = new int[arr.length];

        diff[0] = arr[0];
        for(int i = 1; i < diff.length; i++) {
            diff[i] = arr[i] - arr[i-1];
        }
    }

    /**
     * 给增闭区间增加val（可以是负数）
     * @param i
     * @param j
     * @param val
     */
    public void increment(int i, int j, int val) {
        diff[i] += val;

        if (j + 1 < diff.length) {
            diff[j + 1] -= val;
        }
    }

    public int[] result() {
        int[] result = new int[diff.length];
        result[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            result[i] = result[i - 1] + diff[i];
        }

        return result;
    }
}
