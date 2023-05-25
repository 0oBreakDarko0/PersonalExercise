package org.myself.huawei.machine;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合
 * @author mapengfei
 */
public class Combination {

    public static void main(String[] args) {
        List<List<Integer>> combine = combine(4, 2);

        System.out.println(Arrays.deepToString(combine.toArray()));
    }

    public static List<List<Integer>> combine(int n, int k) {
        int[] nums = new int[n];
        int limit = k;
        for(int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }

        LinkedList<Integer> track = new LinkedList<>();
        boolean[] visited = new boolean[nums.length];
        List<List<Integer>> result = new LinkedList<>();
        int currentIndex = 0;
        permutation(track, visited, nums, result, limit, currentIndex);

        return result;
    }

    public static void permutation(LinkedList<Integer> track, boolean[] visited, int[] nums, List<List<Integer>> result, int limit, int currentIndex) {
        if (track.size() == limit) {
            result.add(new LinkedList<>(track));
            return;
        }

        for (int i = currentIndex; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            track.add(nums[i]);
            currentIndex = i + 1;
            permutation(track, visited, nums, result, limit, currentIndex);
            visited[i] = false;
            track.removeLast();
        }


    }
}
