package com.walle.leetcode.normal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：

所有数字（包括 target）都是正整数。
解集不能包含重复的组合。 
 */
public class LeetCode39 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> stack = new ArrayList<>();
        Arrays.sort(candidates);
        List<List<Integer>> resultList = new ArrayList<>();
        search(candidates, target, stack, resultList, 0);
        return resultList;
    }

    public void search(int[] candidates, int target, List<Integer> stack, List<List<Integer>> resultList, int begin) {
        if (target == 0) {
            resultList.add(new ArrayList<>(stack));
            return;
        }
        for (int i = begin; i < candidates.length; ++i) {
            int remain = target - candidates[i];
            if (remain < 0)
                break;
            stack.add(candidates[i]);
            search(candidates, remain, stack, resultList, i);
            stack.remove(stack.size() - 1);
        }
    }

    public static void main(String[] args) {
        LeetCode39 lc = new LeetCode39();
        int[] nums = {2, 3, 6, 7};
        int target = 7;

        lc.combinationSum(nums, target).forEach(list -> {
            list.forEach(item -> System.out.print(item + ","));
            System.out.println();
        });
    }
}
