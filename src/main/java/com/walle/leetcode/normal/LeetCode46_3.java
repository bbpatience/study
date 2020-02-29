package com.walle.leetcode.normal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
给定一个没有重复数字的序列，返回其所有可能的全排列。
 */
public class LeetCode46_3 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, result, new LinkedList<>());
        return result;
    }

    public void dfs(int[] nums, List<List<Integer>> result, LinkedList<Integer> list) {
        if (nums.length == list.size()) {
            result.add((List<Integer>)list.clone());
            return;
        }
        for (int j = 0; j < nums.length; ++j) {
            if (!list.contains(nums[j])) {
                list.add(nums[j]);
                dfs(nums, result, list);
                list.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        LeetCode46_3 lc = new LeetCode46_3();
        int[] nums = {1, 2, 3};

        lc.permute(nums).forEach(list -> {
            list.forEach(item -> System.out.print(item + ","));
            System.out.println();
        });
    }
}
