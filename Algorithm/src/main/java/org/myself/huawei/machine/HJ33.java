package org.myself.huawei.machine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 整数与IP地址之间的相互转换
 */
public class HJ33 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String ipInput = in.nextLine();
        Long numberInput = in.nextLong();

        System.out.println(transferIpToNumber(ipInput));
        System.out.println(transferNumberToIp(numberInput));
    }

    /**
     * 将十进制整数转化为IP地址
     * @param numberInput
     * @return
     */
    public static String transferNumberToIp(Long numberInput) {
        List<Long> everyIndex = new ArrayList<>();
        //先将整数转化为二进制
        while (numberInput != 0) {
            long current = numberInput % 2;
            everyIndex.add(current);
            numberInput = numberInput / 2;
        }

        int size = everyIndex.size();
        if (size < 32) {
            for (int i = size; i < 32; i++) {
                everyIndex.add(0L);
            }
        }

        Collections.reverse(everyIndex);
        List<List<Long>> ipNumberList = new ArrayList<>();
        int start = 0, end = 8;
        while (end <= everyIndex.size()) {
            ipNumberList.add(everyIndex.subList(start, end));
            start += 8;
            end   += 8;
        }

        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < ipNumberList.size(); i++) {
            List<Long> subIpList = ipNumberList.get(i);
            long result = 0;
            for (int j = 0; j < subIpList.size(); j++) {
                long integer = subIpList.get(j);
                result += (int) (integer * Math.pow(2, subIpList.size() - j - 1));
            }
            resultList.add(String.valueOf(result));
        }

        return String.join(".", resultList);
    }

    /**
     * 将IP地址转化为10进制整数
     * @param input
     * @return
     */
    public static long transferIpToNumber(String input) {
        //根据 . 进行切割
        String[] splitStr = input.split("\\.");
        List<String> binaryStrList = new ArrayList<>();

        Arrays.asList(splitStr).forEach(str -> binaryStrList.addAll(transferBitCount(str)));

        long result = 0;
        int length = binaryStrList.size();
        for (int i = 0; i < length; i++) {
            String current = binaryStrList.get(i);
            long intValue = Long.parseLong(current);
            result += intValue * Math.pow(2, length - i - 1);
        }

        return result;
    }

    /**
     * 将十进制转化为二进制
     *
     * @param str
     * @return
     */
    public static List<String> transferBitCount(String str) {
        int intValue = Integer.parseInt(str);
        List<String> everyIndex = new ArrayList<>();
        while (intValue != 0) {
            int current = intValue % 2;
            everyIndex.add(String.valueOf(current));
            intValue = intValue / 2;
        }
        int length = everyIndex.size();
        if (length < 8) {
            for (int i = length; i < 8; i++) {
                everyIndex.add(String.valueOf(0));
            }
        }
        Collections.reverse(everyIndex);
        return everyIndex;
    }
}