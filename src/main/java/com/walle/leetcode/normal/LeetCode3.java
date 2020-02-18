package com.walle.leetcode.normal;

import java.util.HashSet;
import java.util.Set;

/*
给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class LeetCode3 {

    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>(s.length());
        int max = 0, i = 0, j = 0;

        while (i < s.length() && j < s.length()) {
            if (set.contains(s.charAt(j))) {
                set.remove(s.charAt(i++));
            } else {
                set.add(s.charAt(j++));
                max = Math.max(max, j - i);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LeetCode3 lc = new LeetCode3();
        String input = "abcbcbb";
        System.out.println(lc.lengthOfLongestSubstring(input));
    }
}
