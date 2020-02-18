package com.walle.leetcode.normal;

import java.util.*;

/*
给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

示例:

输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
 */
public class LeetCode49_2 {

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray())
                count[c - 'a']++;

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key))
                ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }

    private String getKey(String str) {
        char[] array = new char[str.length()];
        for (int i = 0; i < str.length(); ++i) {
            array[i] = str.charAt(i);
        }
        Arrays.sort(array);
        return new String(array);
    }

    public static void main(String[] args) {
        LeetCode49_2 lc = new LeetCode49_2();
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        lc.groupAnagrams(input);
        for (List<String> list : lc.groupAnagrams(input)) {
            list.forEach(c -> System.out.print(c + ","));
            System.out.println();
        }
    }
}
