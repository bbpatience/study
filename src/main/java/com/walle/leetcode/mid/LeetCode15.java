package com.walle.leetcode.mid;

import java.util.*;

/*
给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组
 */
public class LeetCode15 {

    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> resultSet = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ++i) {
            // 去重
            resultSet.addAll(find2sum(nums, i));
        }
        return new ArrayList<>(resultSet);
    }

    private List<List<Integer>> find2sum(int[] nums, int idx) {
        List<List<Integer>> result = new ArrayList<>();
        int sum = 0 - nums[idx];
        int i = 0, j = nums.length - 1;
        while (i < j) {
            if (i == idx || nums[i] + nums[j] < sum) {
                ++i;
            } else if (j == idx || nums[i] + nums[j] > sum) {
                --j;
            } else {
                // find
                Integer[] tmp = new Integer[]{nums[i], nums[j], nums[idx]};
                Arrays.sort(tmp);
                result.add(Arrays.asList(tmp));
                ++i;
                --j;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode15 lc = new LeetCode15();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = lc.threeSum(nums);
        for (List<Integer> value : result) {
            value.forEach((item) -> System.out.print(item + " "));
            System.out.println();
        }
    }
}
