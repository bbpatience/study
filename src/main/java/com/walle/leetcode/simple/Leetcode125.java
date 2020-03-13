package com.walle.leetcode.simple;

/*
给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

说明：本题中，我们将空字符串定义为有效的回文串。
 */
public class Leetcode125 {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            while (!isValid(s.charAt(i))) {
                ++i;
            }
            while (!isValid(s.charAt(j))) {
                --j;
            }
            if (s.charAt(i) != s.charAt(j))
                return false;
            ++i;
            --j;
        }
        return true;
    }

    private boolean isValid(char ch) {
        return ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z')) ;
    }

    public static void main(String[] args) {
        Leetcode125 lc = new Leetcode125();
        String input = "A man, a plan, a canal: Panama";
        System.out.println(lc.isPalindrome(input));
    }
}
