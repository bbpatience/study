package com.walle.leetcode.normal;

import java.util.*;

/*
给定一个没有重复数字的序列，返回其所有可能的全排列。
 */
public class LeetCode46 {

    public List<List<Integer>> permute(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        List<List<Integer>> resultList = new ArrayList<>();
        search(nums, nums.length, stack, resultList, set);
        return resultList;
    }

    public void search(int[] nums, int level, Stack<Integer> stack, List<List<Integer>> resultList, Set<Integer> set) {
        if (level == 0) {
            List<Integer> tmp = (List<Integer>) stack.clone();
            resultList.add(tmp);
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
                stack.push(nums[i]);
                search(nums, level - 1, stack, resultList, set);
                stack.pop();
                set.remove(nums[i]);
            }
        }
    }

    public static void main(String[] args) {
        LeetCode46 lc = new LeetCode46();
        int[] nums = {1, 2, 3};

        lc.permute(nums).forEach(list -> {
            list.forEach(item -> System.out.print(item + ","));
            System.out.println();
        });
    }
}
