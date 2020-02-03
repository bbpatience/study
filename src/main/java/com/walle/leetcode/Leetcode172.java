package com.walle.leetcode;

/*
给定一个整数 n，返回 n! 结果尾数中零的数量。

示例 1:

输入: 3
输出: 0
解释: 3! = 6, 尾数中没有零。
示例 2:

输入: 5
输出: 1
解释: 5! = 120, 尾数中有 1 个零.
说明: 你算法的时间复杂度应为 O(log n) 。
 */
public class Leetcode172 {
    public int trailingZeroes(int n) {
        // n / 5 ，5步会有一个0； n/5*5, 25步会再多1个0； n/5*5*5 125步会再多一个0
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n = n / 5;
        }
        return count;
    }

    public static void main(String[] args) {
        Leetcode172 lc = new Leetcode172();
        System.out.println(lc.trailingZeroes(126));
    }
}
