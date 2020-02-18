package com.walle.leetcode.normal;

/*
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

DP
 */
public class LeetCode5 {

    public String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        String res = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (j == i)
                    dp[i][j] = true;
                else if (j - i == 1 && s.charAt(i) == s.charAt(j))
                    dp[i][j] = true;
                else if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    // state transition
                    dp[i][j] = true;
                }

                if (dp[i][j] && j - i + 1 > res.length()) {
                    // update res
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode5 lc = new LeetCode5();
        String input = "abcbcbb";
        System.out.println(lc.longestPalindrome(input));
    }
}
