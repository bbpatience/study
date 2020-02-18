package com.walle.leetcode.normal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组
 */
public class LeetCode15_2 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if (nums == null || len < 3) return ans;
        Arrays.sort(nums); // 排序
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重
            int L = i + 1;
            int R = len - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1])
                        L++; // 去重
                    while (L < R && nums[R] == nums[R - 1])
                        R--; // 去重
                    L++;
                    R--;
                } else if (sum < 0) {
                    L++;
                }
                else {
                    R--;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LeetCode15_2 lc = new LeetCode15_2();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = lc.threeSum(nums);
        for (List<Integer> value : result) {
            value.forEach((item) -> System.out.print(item + " "));
            System.out.println();
        }
    }
}
