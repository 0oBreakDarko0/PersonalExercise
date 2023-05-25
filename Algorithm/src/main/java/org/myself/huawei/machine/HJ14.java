package org.myself.huawei.machine;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 给定 n 个字符串，请对 n 个字符串按照字典序排列。
 * @author mapengfei
 */
public class HJ14 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int strCounts = in.nextInt();

            TreeMap<String, Integer> sortedMap = new TreeMap<>();

            for (int i = 0; i < strCounts; i++) {
                String str = in.next();
                Integer repeatCount = sortedMap.get(str);
                if (repeatCount == null) {
                    sortedMap.put(str, 1);
                } else {
                    repeatCount ++;
                    sortedMap.put(str, repeatCount);
                }
            }

            sortedMap.forEach((str, repeatCount) -> {
                for (int i = 0; i < repeatCount; i++) {
                    System.out.println(str);
                }
            });
        }
    }
}
