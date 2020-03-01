package com.walle.leetcode.normal;

import java.util.*;

/*
给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。
 */
public class LeetCode78 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int level = 0; level < nums.length + 1; ++level) {
            dfs(0, result, level, nums, new ArrayList<>());
        }

        return result;
    }

    public void dfs(int start, List<List<Integer>> result, int level, int[] nums, List<Integer> list) {
        if (level == list.size()) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < nums.length; ++i) {
            if (!list.contains(nums[i])) {
                list.add(nums[i]);
                dfs(start + 1, result, level, nums, list);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        LeetCode78 lc = new LeetCode78();
        int[] nums = {1, 2, 3};

        lc.subsets(nums).forEach(list -> {
            list.forEach(item -> System.out.print(item + ","));
            System.out.println();
        });
    }
}
