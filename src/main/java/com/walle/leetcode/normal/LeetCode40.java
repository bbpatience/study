package com.walle.leetcode.normal;

import java.util.*;

/*
给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

说明：

所有数字（包括 target）都是正整数。
解集不能包含重复的组合。 
 */
public class LeetCode40 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Stack<Integer> stack = new Stack<>();
        Set<List<Integer>> resultList = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        search(candidates, target, stack, resultList, 0, set);
        return new ArrayList<>(resultList);
    }

    public void search(int[] candidates, int target, Stack<Integer> stack, Set<List<Integer>> resultList, int begin, Set<Integer> set) {
        if (target < 0) {
            return;
        } else if (target == 0) {
            List<Integer> tmp = (List<Integer>) stack.clone();
            Collections.sort(tmp);
            resultList.add(tmp);
            return;
        }
        for (int i = begin; i < candidates.length; ++i) {
            if (!set.contains(i)) {
                set.add(i);
                stack.push(candidates[i]);
                search(candidates, target - candidates[i], stack, resultList, i, set);
                stack.pop();
                set.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        LeetCode40 lc = new LeetCode40();
        int[] nums = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;

        lc.combinationSum2(nums, target).forEach(list -> {
            list.forEach(item -> System.out.print(item + ","));
            System.out.println();
        });
    }
}
