package org.myself.huawei.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * 最长公共子串
 *
 * @author mapengfei
 */
public class HJ65 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String firstStr = in.nextLine();
            String secondStr = in.nextLine();

            findMaxLengthChildStr(firstStr, secondStr);
        }
    }

    public static void findMaxLengthChildStr(String firstStr, String secondStr) {
        String sourceStr = firstStr, targetStr = secondStr;
        if (firstStr.length() < secondStr.length()) {
            sourceStr = secondStr;
            targetStr = firstStr;
        }

        int sourceIndex = 0, tempIndex = 0;

        char[] sourceChs = sourceStr.toCharArray();
        char[] targetChs = targetStr.toCharArray();

        List<String> result = new ArrayList<>();
        int length = sourceChs.length;
        while (tempIndex < length) {
            StringBuilder tempStr = new StringBuilder();

            for (int j = 0; j < targetChs.length; j++) {

                if (tempIndex < length) {
                    if (sourceChs[tempIndex] == targetChs[j]) {
                        tempStr.append(sourceChs[tempIndex]);
                        tempIndex++;
                    } else {

                        if (!"".equals(tempStr.toString())) {
                            result.add(tempStr.toString());
                            tempStr = new StringBuilder();
                        }

                        tempIndex = sourceIndex;
                    }
                }

            }


            if (!"".equals(tempStr.toString())) {
                result.add(tempStr.toString());
            }
            sourceIndex++;
            tempIndex = sourceIndex;
        }

        Collections.sort(result, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });

        //System.out.println(Arrays.deepToString(result.toArray()));

        if (result.size() > 0) {
            String maxStr = result.get(0);
            int maxLen = maxStr.length();
            int maxIndex = targetStr.indexOf(maxStr);
            for (int i = 1; i < result.size(); i++) {
                String tempStr = result.get(i);
                if (tempStr.length() == maxLen) {
                    int curIndex = targetStr.indexOf(tempStr);
                    if (maxIndex > curIndex) {
                        maxIndex = curIndex;
                        maxStr = tempStr;
                    }
                }
            }

            System.out.println(maxStr);
        }

    }
}
