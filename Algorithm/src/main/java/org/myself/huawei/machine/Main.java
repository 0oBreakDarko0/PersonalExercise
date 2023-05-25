package org.myself.huawei.machine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String answer = in.nextLine();
            String[] answers = answer.split(",");
            Set<String> answerSet = new HashSet<>();
            for (int i = 0; i < answers.length; i++) {
                String curAnswer = answers[i];
                answerSet.add(solution(curAnswer));
            }

            String source = in.nextLine();
            String[] sourceString = source.split(",");
            List<String> result = new ArrayList<>();
            for (int i = 0; i < sourceString.length; i++) {
                String curSource = sourceString[i];
                if (answerSet.contains(solution(curSource))) {
                    result.add(curSource);
                }
            }

            if (result.size() == answerSet.size()) {
                System.out.println(String.join(",", result));
            } else {
                System.out.println("not found");
            }
        }
    }

    private static String solution(String source) {
        //对字符串进行去重和排序
        Set<Character> result = new TreeSet<>();
        char[] chars = source.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            result.add(chars[i]);
        }

        StringBuilder sb = new StringBuilder();
        result.forEach(sb::append);

        return sb.toString();
    }
}
