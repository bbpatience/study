package com.walle.leetcode.normal;

import java.util.*;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class Leecode17 {

    private Map<Character, String> map;

    public List<String> letterCombinations(String digits) {
        map = getBasicMap();
        List<String> result = new LinkedList<>();
        if (digits.length() > 0)
            dfs("", digits, result, 0);
        return result;
    }

    private Map<Character, String> getBasicMap() {
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        return map;
    }

    private void dfs(String prefix, String input, List<String> result, int idx) {
        if (prefix.length() == input.length()) {
            result.add(prefix);
            return;
        }
        String s = map.get(input.charAt(idx));
        for (int i = 0; i < s.length(); ++i) {
            dfs(prefix + s.charAt(i), input, result, idx + 1);
        }
    }

    public static void main(String[] args) {
        Leecode17 lc = new Leecode17();
        String input = "23";
        List<String> outputList = lc.generateParenthesis(3);
        outputList.forEach(output -> {
            System.out.print(output + " ");
        });
//        System.out.print(lc.isValid("(())()"));
    }

    private boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '(')
                stack.push(s.charAt(i));
            else {
                if (stack.isEmpty() || stack.peek() != '(')
                    return false;
                else
                    stack.pop();
            }
            ++i;
        }
        return stack.isEmpty();
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new LinkedList<>();
        if (n > 0) {
            dfs("", result, n, n);
        }
        return result;
    }

    private void dfs(String pre, List<String> result, int left, int right) {
        if (left == 0 && right == 0) {
            result.add(pre);
            return;
        }
        if (left > 0)
            dfs(pre + "(", result, left - 1, right);
        if (right > left && right > 0)
            dfs(pre + ")", result, left, right - 1);
    }
}
