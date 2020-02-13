package com.walle.leetcode.simple;

/**
 * 给你一个字符串 s，它仅由字母 'a' 和 'b' 组成。每一次删除操作都可以从 s 中删除一个回文 子序列。
 * <p>
 * 返回删除给定字符串中所有字符（字符串为空）的最小删除次数。
 */
public class Leetcode1332 {
    public int removePalindromeSub(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Leetcode125 lc = new Leetcode125();
        if (lc.isPalindrome(s)) {
            return 1;
        }
        return 2;
    }

    public static void main(String[] args) {
        Leetcode1332 lc = new Leetcode1332();
        String s = "ababab";
        System.out.println(lc.removePalindromeSub(s));
    }
}
