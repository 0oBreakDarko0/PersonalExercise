package org.myself.huawei.machine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Leetcode数组全排列问题
 * @author mapengfei
 */
public class Permutations {
    private static List<List<Integer>> result = new LinkedList<>();

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        permute(nums);

        System.out.println(Arrays.deepToString(result.toArray()));
    }

    public static List<List<Integer>> permute(int[] nums) {
        /**
         * 全排列问题一般使用回溯算法结合DFS进行实现
         */
        //记录路径
        LinkedList<Integer> track = new LinkedList<>();
        //选择列表
        boolean[] choose = new boolean[nums.length];
        backtrack(track, choose, nums);

        return result;
    }

    public static void backtrack(LinkedList<Integer> track, boolean[] choose, int[] nums) {
        if (track.size() == nums.length) {
            result.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (choose[i]) {
                continue;
            }

            track.add(nums[i]);
            choose[i] = true;
            backtrack(track, choose, nums);
            track.removeLast();
            choose[i] = false;
        }
    }
}
