package org.myself.huawei.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 查找兄弟单词
 * @author mapengfei
 */
public class HJ27 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            //字符串个数
            int strNums = in.nextInt();

            List<String> sourceList = new ArrayList<>();
            for (int i = 0; i < strNums; i++) {
                String sourceStr = in.next();
                sourceList.add(sourceStr);
            }

            String targetStr = in.next();
            int whichNumber = in.nextInt();

            int isBrotherStrCount = 0;
            List<String> brotherList = new ArrayList<>();
            for (String str : sourceList) {
                if (isBrotherStr(str, targetStr)) {
                    isBrotherStrCount ++;
                    brotherList.add(str);
                }
            }

            Collections.sort(brotherList);


            System.out.println(isBrotherStrCount);


            if (whichNumber <= brotherList.size()) {
                System.out.println(brotherList.get(whichNumber - 1));
            }
        }
    }

    /**
     * 判断是否是兄弟字符串
     * @param sourceStr 原字符串
     * @param targetStr 目标字符串
     * @return 是否是兄弟字符串
     */
    public static boolean isBrotherStr(String sourceStr, String targetStr) {
        //首先判断字符串的长度
        if (sourceStr.length() != targetStr.length()) {
            return false;
        }

        if (sourceStr.equals(targetStr)) {
            return false;
        }

        if (recombination(sourceStr).equals(recombination(targetStr))) {
            return true;
        }

        return false;
    }

    /**
     * 将字符串按照字典顺序进行重组
     * @param str
     * @return
     */
    public static String recombination(String str) {
        char[] chs = str.toCharArray();
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < chs.length; i++) {
            characters.add(chs[i]);
        }

        Collections.sort(characters);
        //再将字符拼接为字符串
        StringBuilder sb = new StringBuilder();
        characters.forEach(ch -> sb.append(String.valueOf(ch)));

        return sb.toString();
    }
}
