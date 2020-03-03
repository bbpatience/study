package com.walle.leetcode.simple;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个段落 (paragraph) 和一个禁用单词列表 (banned)。返回出现次数最多，同时不在禁用列表中的单词。题目保证至少有一个词不在禁用列表中，而且答案唯一。
 * <p>
 * 禁用列表中的单词用小写字母表示，不含标点符号。段落中的单词不区分大小写。答案都是小写字母。
 */
public class Leetcode819 {

    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (String s : banned) {
            set.add(s);
        }
        int j = 0;
        for (int i = 0; i < paragraph.length(); ++i) {
            char c = paragraph.charAt(i);
            if (!isValid(c)) {
                String key = paragraph.substring(j, i).toLowerCase();
                if (!set.contains(key) && key.length() > 0)
                    map.put(key, map.getOrDefault(key, 0) + 1);
                j = i + 1;
            }
            if (i == paragraph.length() - 1 && isValid(c)) {
                String key = paragraph.substring(j, i + 1).toLowerCase();
                if (!set.contains(key) && key.length() > 0)
                    map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
        String result = "";
        int max = 0;
        for (String key : map.keySet()) {
            if (map.get(key) > max) {
                max = map.get(key);
                result = key;
            }
        }
        return result;
    }

    private boolean isValid(char c) {
        return (c <= 'Z' && c >= 'A') || (c <= 'z' && c >= 'a');
    }

    public static void main(String[] args) {
        String paragraph = "Bob. hIt, baLl";
        String[] banned = {"bob", "hit"};
        Leetcode819 lc = new Leetcode819();
        String result = lc.mostCommonWord(paragraph, banned);
        System.out.println(result);
    }
}
