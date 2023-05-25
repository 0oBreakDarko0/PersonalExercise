package org.myself.huawei.machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 将一个字符串str的内容颠倒过来，并输出。
 * @author mapengfei
 */
public class HJ106 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String input = in.nextLine();
            System.out.println(reverseStr(input));
        }
    }

    /**
     * 将字符串逆向输出
     * @param str 原始字符串
     * @return 逆向后的字符串
     */
    public static String reverseStr(String str) {
        char[] chs = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = chs.length - 1; i >= 0; i--) {
            sb.append(String.valueOf(chs[i]));
        }

        return sb.toString();
    }
}
