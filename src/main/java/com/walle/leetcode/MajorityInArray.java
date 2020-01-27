package com.walle.leetcode;

import java.util.Arrays;

public class MajorityInArray {
    public static int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
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
            if (nums[i] == nums[i-1]) {
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
    public static void main(String[] args) {
        int[] nums = {7, 7, 5, 7, 5, 1, 5, 7, 5, 5, 7, 7, 7, 7, 7, 7};
        System.out.println(majorityElement2(nums));
    }
}
