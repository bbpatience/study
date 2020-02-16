package com.walle.leetcode.mid;

import java.util.*;

/*
给定一个没有重复数字的序列，返回其所有可能的全排列。
 */
public class LeetCode46_2 {

    public List<List<Integer>> permute(int[] nums) {
        Stack<Integer> stack = new Stack<>();
//        Arrays.sort(nums);
        List<List<Integer>> resultList = new ArrayList<>();
        search(nums, stack, resultList);
        return resultList;
    }

    public void search(int[] nums, Stack<Integer> stack, List<List<Integer>> resultList) {
        if (nums.length == stack.size()) {
            List<Integer> tmp = (List<Integer>) stack.clone();
            resultList.add(tmp);
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (stack.contains(nums[i]))
                continue;
            stack.push(nums[i]);
            search(nums, stack, resultList);
            stack.pop();
        }
    }

    public static void main(String[] args) {
        LeetCode46_2 lc = new LeetCode46_2();
        int[] nums = {1, 2, 3};

        lc.permute(nums).forEach(list -> {
            list.forEach(item -> System.out.print(item + ","));
            System.out.println();
        });
    }
}
