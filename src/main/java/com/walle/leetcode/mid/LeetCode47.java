package com.walle.leetcode.mid;

import java.util.*;

/*
给定一个可包含重复数字的序列，返回所有不重复的全排列
 */
public class LeetCode47 {

    public List<List<Integer>> permute(int[] nums) {
        Stack<Integer> stack = new Stack<>();
//        Arrays.sort(nums);
        Set<List<Integer>> resultList = new HashSet<>();
        search(nums, stack, resultList);
        return new ArrayList<>(resultList);
    }

    public void search(int[] nums, Stack<Integer> stack, Set<List<Integer>> resultList) {
        if (nums.length == stack.size()) {
            List<Integer> tmp = new ArrayList<>();
            stack.forEach(i -> tmp.add(nums[i]));
            resultList.add(tmp);
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (stack.contains(i))
                continue;
            stack.push(i);
            search(nums, stack, resultList);
            stack.pop();
        }
    }

    public static void main(String[] args) {
        LeetCode47 lc = new LeetCode47();
        int[] nums = {1, 1, 2};

        lc.permute(nums).forEach(list -> {
            list.forEach(item -> System.out.print(item + ","));
            System.out.println();
        });
    }
}
