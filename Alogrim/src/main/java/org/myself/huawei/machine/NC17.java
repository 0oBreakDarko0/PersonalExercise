package org.myself.huawei.machine;

/**
 * 对于长度为n的一个字符串A（仅包含数字，大小写英文字母），请设计一个高效算法，计算其中最长回文子串的长度。
 */
public class NC17 {
    public static void main(String[] args) {
        System.out.println(getLongestPalindrome("cbc"));
    }

    public static int getLongestPalindrome(String a) {
        char[] chars = a.toCharArray();
        int left = 0;
        int maxLength = 1;

        while (left < chars.length) {
            int currentLength = 0;
            for (int i = left; i < chars.length; i++) {
                if (validPalindrome(chars, left, i)) {
                    currentLength = i - left + 1;
                }
            }

            maxLength = Math.max(maxLength, currentLength);

            left ++;
        }

        return maxLength;
    }

    public static boolean validPalindrome(char[] targetChs, int left, int right) {
        StringBuilder leftToRight = new StringBuilder();
        for (int i = left; i <= right; i++) {
            leftToRight.append(targetChs[i]);
        }

        StringBuilder rightToLeft = new StringBuilder();
        for (int i = right; i >= left; i--) {
            rightToLeft.append(targetChs[i]);
        }

        return leftToRight.toString().equals(rightToLeft.toString());
    }
}
