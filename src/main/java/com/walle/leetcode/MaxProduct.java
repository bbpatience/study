package com.walle.leetcode;

//leetcode 152
public class MaxProduct {
    private static int maxProduct(int[] nums) {
        if (nums.length == 0)
            return 0;
        else if (nums.length == 1)
            return nums[0];
        int res = nums[0];
        //状态定义： 1位数组存储从0到i中，每个节点的最大乘积值Max，2位数组存储最大负值-Max
        int[][] dp = new int[nums.length][2];
        //初始化定义
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][1] * nums[i], Math.max(dp[i - 1][0] * nums[i], nums[i]));
            dp[i][1] = Math.min(dp[i - 1][1] * nums[i], Math.min(dp[i - 1][0] * nums[i], nums[i]));

            res = Math.max(dp[i][0], res);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] input = {2, 3, -2, 4};
        System.out.println(maxProduct(input));
    }
}
