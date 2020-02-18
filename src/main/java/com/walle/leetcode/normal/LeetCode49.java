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
public class LeetCode49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = String.valueOf(array);

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        LeetCode49 lc = new LeetCode49();
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        lc.groupAnagrams(input);
        for (List<String> list : lc.groupAnagrams(input)) {
            list.forEach(c -> System.out.print(c + ","));
            System.out.println();
        }
    }
}
