package org.myself.huawei.machine;

import java.util.*;


public class NC52 {
    public static void main(String[] args) {
        System.out.println(isValid("}(])[{(}([[}])}]))})]]({{(])"));
    }
    /**
     *
     * @param s string字符串
     * @return bool布尔型
     */
    public static boolean isValid (String s) {
        // write code here
        if (s.length() % 2 == 1)  {
            return false;
        }

        char[] chs = s.toCharArray();
        Map<String, String> bracketMap = new HashMap() {
            {
                put(")", "(");
                put("]", "[");
                put("}", "{");
            }
        };

        Set<String> leftBracket = new HashSet() {
            {
                add("(");
                add("[");
                add("{");
            }
        };


        LinkedList<String> stack = new LinkedList<>();
        for (int i = 0; i < chs.length; i++) {
            String curBracket = String.valueOf(chs[i]);
            if(!leftBracket.contains(curBracket)) {
                String rightBracket = bracketMap.get(curBracket);

                if (stack.isEmpty()) {
                    return false;
                }

                String tempBracket = stack.removeLast();

                if(!tempBracket.equals(rightBracket)) {
                    return false;
                }
            } else {
                stack.addLast(curBracket);
            }
        }


        if(!stack.isEmpty()) {
            return false;
        }
        return true;
    }
}
