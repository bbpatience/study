package com.walle.leetcode;

// leetcode 300
public class LongestIncreasingSeq {
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int max = 1;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            dp[i] = 1;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= i - 1; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] input = {10, 9, 2, 5, 3, 7, 101, 18, 20};
        System.out.println(lengthOfLIS(input));
    }
}
