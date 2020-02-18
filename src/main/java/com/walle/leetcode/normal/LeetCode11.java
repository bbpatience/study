package com.walle.leetcode.normal;

/*
给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。


暴力
 */
public class LeetCode11 {

    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length - 1; ++i) {
            for (int j = i + 1; j < height.length; ++j) {
                max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LeetCode11 lc = new LeetCode11();
//        int[] input = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] input = {9, 1};
        System.out.println(lc.maxArea(input));
    }
}
