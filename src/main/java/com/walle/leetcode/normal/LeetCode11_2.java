package com.walle.leetcode.normal;

/*
给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

DP
 */
public class LeetCode11_2 {

    public int maxArea(int[] height) {
        int[] dp = new int[height.length];
        dp[0] = 0;
        for (int i = 1; i < height.length; ++i) {
            dp[i] = Math.max(dp[i - 1], max(height, i));
        }
        return dp[height.length - 1];
    }

    public int max(int[] height, int i) {
        int max = 0;
        for (int j = 0; j < i; ++j) {
            max = Math.max(max, (i - j) * Math.min(height[i], height[j]));
        }
        return max;
    }

    public static void main(String[] args) {
        LeetCode11_2 lc = new LeetCode11_2();
        int[] input = {1, 8, 6, 2, 5, 4, 8, 3, 7};
//        int[] input = {1, 1};
        System.out.println(lc.maxArea(input));
    }
}
