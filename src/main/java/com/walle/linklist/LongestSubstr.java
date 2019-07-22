package com.walle.linklist;

/**
 * @author: bbpatience
 * @date: 2019/7/21
 * @description: LongestSubstr
 **/
public class LongestSubstr {

    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int maxLen = 0;
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            int k = 0;
            int j = i + 1;
            while (j < s.length()) {
                if (s.charAt(j) == s.charAt(i)) {
                    k = j;
                }
                j++;
            }
            if (k - i >= maxLen) {
                maxLen = k - i;
                result = s.substring(i, k + 1);
            }
        }
        if (result.length() == 0) {
            return s.substring(0,1);
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(longestPalindrome("ccc"));
    }
}
