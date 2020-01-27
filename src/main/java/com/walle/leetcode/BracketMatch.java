package com.walle.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BracketMatch {
    private static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        Map<Character, Character> mapping = new HashMap<>(3);
        mapping.put(')', '(');
        mapping.put(']', '[');
        mapping.put('}', '{');

        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);

            if (!mapping.containsKey(ch)) {
                //是左括号则入栈
                stack.push(ch);
            } else if (stack.isEmpty()) {
                // 若是空又进来的是右括号，则非法
                return false;
            } else {
                if (stack.peek() == mapping.get(ch)) {
                    // 若栈中有元素，则比较； 若是匹配的，则弹出；若匹配则非法；
                    stack.pop();
                } else
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "{[]}";
        System.out.println(isValid(s));
    }
}
