package org.myself.huawei.machine;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 16进制转为10进制
 */
public class HexExchange {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map<String, Integer> hexMap = new HashMap(){{
            put("A", 10);
            put("B", 11);
            put("C", 12);
            put("D", 13);
            put("E", 14);
            put("F", 15);
        }};
        while (in.hasNext()) {
            String cur = in.nextLine();
            cur = cur.replaceFirst("0x", "");

            int result = 0;
            char[] chs = cur.toCharArray();

            for (int i = 0; i < chs.length; i++) {
                String curItem = String.valueOf(chs[i]);
                int curValue = 0;
                if (hexMap.containsKey(curItem)) {
                    curValue = hexMap.get(curItem);
                } else {
                    curValue = Integer.parseInt(curItem);
                }

                result += curValue * Math.pow(16, chs.length -i - 1);
            }

            System.out.println(result);
        }
    }
}
