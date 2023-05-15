package org.myself.huawei.machine;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * 明明生成了N个1到500之间的随机整数。请你删去其中重复的数字，
 * 即相同的数字只保留一个，把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。
 */
public class RandomNum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            //个数
            int nums = in.nextInt();

            Set<Integer> result = new TreeSet<>();
            for (int i = 0; i < nums; i++) {
                int cur = in.nextInt();
                result.add(cur);
            }

            result.forEach(System.out::println);
        }
    }
}
