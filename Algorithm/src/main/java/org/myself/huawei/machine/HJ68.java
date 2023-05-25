package org.myself.huawei.machine;

import java.util.*;

/**
 * 成绩排序
 */
public class HJ68 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while(in.hasNext()) {
            int peopleNums = in.nextInt();
            int orderFlag = in.nextInt();

            List<Map<String, Integer>> scoreMapList = new ArrayList<>();
            for (int i = 0; i < peopleNums; i++) {
                Map<String, Integer> scoreMap = new HashMap<>();
                String name = in.next();
                int score = in.nextInt();
                scoreMap.put(name, score);

                scoreMapList.add(scoreMap);
            }

            switch (orderFlag) {
                case 0:
                    orderByDesc(scoreMapList);
                    break;
                case 1:
                    orderByAsc(scoreMapList);
                    break;
            }

            scoreMapList.forEach(scoreMap -> {
                scoreMap.forEach((name, score) -> {
                    System.out.println(name + " " + score);
                });
            });
        }
    }

    public static void orderByAsc (List<Map<String, Integer>> scoreMapList) {
        Collections.sort(scoreMapList, new Comparator<Map<String, Integer>>() {
            @Override
            public int compare(Map<String, Integer> o1, Map<String, Integer> o2) {
                List<Integer> valuesOfO1 = new ArrayList<>(o1.values());
                List<Integer> valuesOfO2 = new ArrayList<>(o2.values());

                return valuesOfO1.get(0) - valuesOfO2.get(0);
            }
        });
    }

    public static void orderByDesc (List<Map<String, Integer>> scoreMapList) {
        Collections.sort(scoreMapList, new Comparator<Map<String, Integer>>() {
            @Override
            public int compare(Map<String, Integer> o1, Map<String, Integer> o2) {
                List<Integer> valuesOfO1 = new ArrayList<>(o1.values());
                List<Integer> valuesOfO2 = new ArrayList<>(o2.values());

                return valuesOfO2.get(0) - valuesOfO1.get(0);
            }
        });
    }
}
