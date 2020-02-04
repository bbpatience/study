package com.walle.leetcode;

import java.util.Arrays;

/*
给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 */

public class Leetcode169 {
    // 投票算法
    public static int majorityElement(int[] nums) {
        int count = 0;
        int candidate = Integer.MIN_VALUE;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1; //同则+1，不同则-1. 因为存在大于n/2的数，最终candidate会变为candidate。
        }

        return candidate;
    }

    public static int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        int count = 1;
        int candidate = nums[0];
        int candidateCnt = 1;
        int i = 1;
        while (i < nums.length) {
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                if (count > candidateCnt) {
                    candidate = nums[i - 1];
                    candidateCnt = count;
                }
                count = 1;
            }
            i++;
        }
        if (count > candidateCnt) {
            candidate = nums[i - 1];
        }
        return candidate;
    }

    public static int majorityElement3(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public static void main(String[] args) {
        int[] nums = {7, 7, 5, 7, 5, 1, 5, 7, 5, 5, 7, 7, 7, 7, 7, 7};
        System.out.println(majorityElement3(nums));
    }
}
