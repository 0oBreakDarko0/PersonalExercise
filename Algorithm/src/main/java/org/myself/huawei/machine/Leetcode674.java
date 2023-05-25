package org.myself.huawei.machine;

/**
 * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
 * @author mapengfei
 */
public class Leetcode674 {

    public static void main(String[] args) {
        int[] nums = {1,3,5,4,7};
        System.out.println(findLengthOfLCIS(nums));
    }

    public static int findLengthOfLCIS(int[] nums) {
        int left = 0;
        int maxLength = 1;
        while (left < nums.length) {
            int length = 1;
            boolean flag = true;
            for (int i = left; i < nums.length; i++) {
                if (i > left) {
                    if (nums[i] > nums[i - 1] && flag) {
                        length ++;
                    } else {
                        flag = false;
                    }
                }
            }

            left ++;

            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }
}
