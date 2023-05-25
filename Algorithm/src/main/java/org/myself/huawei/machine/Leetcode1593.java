package org.myself.huawei.machine;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 拆分字符串使唯一子字符串的数目最大
 */
public class Leetcode1593 {

    int maxSplit = 1;

    public static void main(String[] args) {
        Leetcode1593 solution = new Leetcode1593();
        solution.maxUniqueSplit("wwwzfvedwfvhsww");
    }

    public int maxUniqueSplit(String s) {
        Set<String> set = new HashSet<>();
        backtrack(0, 0, s, set);
        return maxSplit;
    }

    public void backtrack(int index, int split, String s, Set<String> set) {
        int length = s.length();
        if (index >= length) {
            maxSplit = Math.max(maxSplit, split);
        } else {
            for (int i = index; i < length; i++) {
                String substr = s.substring(index, i + 1);
                if (set.add(substr)) {
                    backtrack(i + 1, split + 1, s, set);
                    set.remove(substr);
                }
            }
        }
    }

}
