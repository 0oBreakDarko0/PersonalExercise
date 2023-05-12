package org.myself.huawei.machine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * HJ41.称砝码
 */
public class HJ41 {
    private List<List<Integer>> results = new ArrayList<>();
    public static void main(String[] args) {
        HJ41 solution = new HJ41();
        System.out.println(solution.solution());
    }

    private int solution() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            //几种砝码
            int weightTypes = in.nextInt();
            //砝码质量
            int[] weightMass = new int[weightTypes];
            //砝码数量
            int[] weightCounts = new int[weightTypes];

            for (int i = 0; i < weightTypes; ++i) {
                weightMass[i] = in.nextInt();
            }

            for (int i = 0; i < weightTypes; ++i) {
                weightCounts[i] = in.nextInt();
            }

            List<Integer> weightDetail = new ArrayList<>();

            for (int i = 0; i < weightTypes; i++) {
                int currentWeight = weightMass[i];
                int currentWeightCount = weightCounts[i];

                for (int j = 0; j < currentWeightCount; j++) {
                    weightDetail.add(currentWeight);
                }
            }

            //转化为数组
            Integer[] weightDetailArr = weightDetail.toArray(new Integer[weightDetail.size()]);
            //路径
            LinkedList<Integer> track = new LinkedList<>();
            boolean[] visited = new boolean[weightDetail.size()];
            dfs(track, visited, weightDetailArr, 1, 0);

            Set<Integer> finalResult = new HashSet<>();
            for (List<Integer> cur : results) {
                Integer integer = cur.stream().reduce(Integer::sum).get();
                finalResult.add(integer);
            }

            return finalResult.size();
        }

        return 1;
    }

    private void dfs(LinkedList<Integer> track, boolean[] visited, Integer[] weightDetailArr, int limit, int currentStart) {
        if (track.size() == limit) {
            results.add(track);
            return;
        }

        for (int i = currentStart; i < weightDetailArr.length; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            track.add(weightDetailArr[i]);
            dfs(track, visited, weightDetailArr, limit + 1, i + 1);
            visited[i] = false;
            track.removeLast();
        }
    }

}
