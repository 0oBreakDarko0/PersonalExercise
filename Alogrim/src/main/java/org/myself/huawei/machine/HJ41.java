package org.myself.huawei.machine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * HJ41.称砝码
 */
public class HJ41 {
    private List<List<Integer>> results = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            HashSet<Integer> set = new HashSet<>();//存放所有可能的结果，不用担心重复问题
            set.add(0);//初始化为0
            //个数
            int n = in.nextInt();
            int[] w = new int[n];
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                //砝码的重量
                w[i] = in.nextInt();
            }
            for (int i = 0; i < n; i++) {
                //砝码个数
                nums[i] = in.nextInt();
            }

            //遍历砝码
            for (int i = 0; i < n; i++) {
                ArrayList<Integer> list = new ArrayList<>(set);//取当前所有的结果

                for (int j = 1; j <= nums[i]; j++) {//遍历个数
                    for (int k = 0; k < list.size(); k++) {
                        set.add(list.get(k) + w[i] * j);
                    }
                }

            }

            System.out.println(set.size());
        }
    }
}
