package org.myself.huawei.machine;

import java.util.*;

/**
 * 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * \
 */

public class NC37 {
    public static void main(String[] args) {
        int[][] intervals = {
                {1, 4},
                {0, 4}
        };
        List<List<Integer>> sortIntervals = sortIntervals(intervals);
        System.out.println(Arrays.deepToString(merge(sortIntervals)));
    }

    /**
     * 先对区间按照start进行排序，那么能够合并的区间一定是连续的
     * @param intervals 多个区间集合
     */
    public static List<List<Integer>> sortIntervals (int[][] intervals) {
        Map<Integer, List<Integer>> sortByStart = new TreeMap<>();

        for (int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            //先判断此起始值是否存在
            if (sortByStart.containsKey(start)) {
                List<Integer> currentStartInterVal = sortByStart.get(start);
                //获取当前区间的结束值
                int currentStartEnd = currentStartInterVal.get(1);
                currentStartInterVal.add(1, Math.max(end, currentStartEnd));
            } else {
                sortByStart.put(start, new ArrayList(){
                    {
                        add(start);
                        add(end);
                    }
                });
            }
        }

        return new ArrayList<>(sortByStart.values());
    }

    public static int[][] merge(List<List<Integer>> sortIntervals) {
        List<List<Integer>> resultList = new ArrayList<>();

        int end = Integer.MIN_VALUE;
        for(List<Integer> cur : sortIntervals) {
            int currentStart = cur.get(0);
            int currentEnd = cur.get(1);

            //如果当前的起始值大于上一个的结束值，则为一个独立的区间
            if (currentStart > end) {
                List<Integer> currentInterval = new ArrayList() {{
                    add(currentStart);
                    add(currentEnd);
                }};

                resultList.add(currentInterval);
            } else {
                //判断结束值，如果结束值大于上一区间的结束值，就合并
                if (currentEnd > end) {
                    List<Integer> lastInterval = resultList.get(resultList.size() - 1);
                    lastInterval.add(1, currentEnd);
                }
            }

            end = Math.max(currentEnd, end);
        }

        int[][] resultMergeArray = new int[resultList.size()][2];

        for (int i = 0; i < resultList.size(); i++) {
            List<Integer> cur = resultList.get(i);
            resultMergeArray[i][0] = cur.get(0);
            resultMergeArray[i][1] = cur.get(1);
        }

        return resultMergeArray;
    }
}
