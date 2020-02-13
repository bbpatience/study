package com.walle.leetcode.simple;

/**
 * 给定一个整数 (32 位有符号整数)，请编写一个函数来判断它是否是 4 的幂次方。
 */
public class Leetcode342 {
    public boolean isPowerOfFour(int num) {
        while (num > 1 && (num % 4 == 0)) {
            num /= 4;
        }
        return num == 1;
    }

    public static void main(String[] args) {
        Leetcode342 lc = new Leetcode342();
        System.out.println(lc.isPowerOfFour(32));
    }
}
