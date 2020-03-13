package com.walle.leetcode.simple;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 */
public class Leetcode53 {
    // DP.
    public int maxSubArray(int[] nums) {
        /* dp[i] - 表示到当前位置 i 的最大子序列和

        状态转移方程为： dp[i] = max(dp[i - 1] + nums[i], nums[i])

        初始化：dp[0] = nums[0]

        从状态转移方程中，我们只关注前一个状态的值，所以不需要开一个数组记录位置所有子序列和，只需要两个变量，

        currMaxSum - 累计最大和到当前位置i

        maxSum - 全局最大子序列和:

        currMaxSum = max(currMaxSum + nums[i], nums[i])
        maxSum = max(currMaxSum, maxSum) */
        int max = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(dp[i], max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] input = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        Leetcode53 lc = new Leetcode53();
        System.out.println(lc.maxSubArray(input));
    }
}
