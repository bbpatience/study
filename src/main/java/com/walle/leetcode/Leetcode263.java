package com.walle.leetcode;

import java.util.Arrays;

/**
 * 编写一个程序判断给定的数是否为丑数。
 * 丑数就是只包含质因数 2, 3, 5 的正整数。
 * }
 */
public class Leetcode263 {
    public boolean isUgly(int num) {
        if (num <= 0)
            return false;
        for (int i : Arrays.asList(5, 3, 2)) {
            while (num % i == 0) {
                num /= i;
            }
        }
        return num == 1;
    }

    public static void main(String[] args) {
        Leetcode263 lc = new Leetcode263();
        System.out.println(lc.isUgly(14));
    }
}
