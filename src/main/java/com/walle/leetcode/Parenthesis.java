package com.walle.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Parenthesis {
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        gen(result, "", n, n);
        return result;
    }

    private static void gen(List<String> result, String subString, int left, int right) {
        if (left == 0 && right == 0) {
            result.add(subString);
            return;
        }
        if (left > 0)
            gen(result, subString + "(", left - 1, right);
        if (right > left)
            gen(result, subString + ")", left, right - 1);
    }

    public static void main(String[] args) {
        List<String> result = generateParenthesis(3);
        result.forEach((item) -> System.out.print(item + " "));
        System.out.println();
    }
}
