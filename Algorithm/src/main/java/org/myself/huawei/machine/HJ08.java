package org.myself.huawei.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 数据表记录包含表索引index和数值value（int范围的正整数），请对表索引相同的记录进行合并，
 * 即将相同索引的数值进行求和运算，输出按照index值升序进行输出。
 * @author mapengfei
 */
public class HJ08 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            //键值对个数
            int keyPairNums = in.nextInt();
            Map<Integer, Integer> map = new HashMap<>(64);
            for (int i = 0; i < keyPairNums; i++) {
                int index = in.nextInt();
                int value = in.nextInt();

                if (map.containsKey(index)) {
                    Integer existIndexValue = map.get(index);
                    map.put(index, value + existIndexValue);
                } else {
                    map.put(index, value);
                }
            }

            printBySortIndex(sortKeys(map.keySet()) , map);
        }
    }

    public static void printBySortIndex(Integer[] sortIndex, Map<Integer, Integer> map) {
        for (Integer index : sortIndex) {
            System.out.print(index + " " + map.get(index) + "\r\n");
        }
    }

    /**
     * 将index进行排序
     * @param keys 未排序的index
     * @return 排序后的index
     */
    public static Integer[] sortKeys(Set<Integer> keys) {
        List<Integer> keyList = new ArrayList<>(keys);
        Integer[] keyArray = keyList.toArray(new Integer[keyList.size()]);

        for (int i = 0; i < keyArray.length - 1; i++) {
            for (int j = i + 1; j < keyArray.length; j++) {
                if (keyArray[i] > keyArray[j]) {
                    int temp = keyArray[j];
                    keyArray[j] = keyArray[i];
                    keyArray[i] = temp;
                }
            }
        }

        return keyArray;
    }
}
