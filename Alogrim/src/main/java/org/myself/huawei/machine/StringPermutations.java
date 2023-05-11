package org.myself.huawei.machine;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 字符串全排列问题
 * @author mapengfei
 */
public class StringPermutations {

    private static final List<List<Character>> result = new LinkedList<>();

    public static void main(String[] args) {
        String s = "pppppHHHH";
        String[] permutation = permutation(s);

        for (int i = 0; i < permutation.length; i++) {
            System.out.println(permutation[i]);
        }
    }

    public static String[] permutation(String s) {
        char[] chars = s.toCharArray();
        LinkedList<Character> track = new LinkedList<>();
        boolean[] choose = new boolean[s.length()];
        Arrays.sort(chars);
        dfs(track, choose, chars);

        Set<String> resultSet = new HashSet<>();

        for (int i = 0; i < result.size(); i++) {
            List<Character> characters = result.get(i);
            StringBuilder sb = new StringBuilder();
            characters.forEach(sb::append);
            resultSet.add(sb.toString());
        }

        return resultSet.toArray(new String[resultSet.size()]);
    }

    public static void dfs(LinkedList<Character> track, boolean[] choose, char[] target) {

        if (track.size() == target.length) {
            result.add(new LinkedList(track));
            return;
        }

        for (int i = 0; i < target.length; i++) {

            if (choose[i]) {
                continue;
            }

            if (i > 0 && target[i] == target[i - 1] && !choose[i - 1]) {
                continue;
            }

            choose[i] = true;
            track.add(target[i]);
            dfs(track, choose, target);
            choose[i] = false;
            track.removeLast();
        }
    }
}
