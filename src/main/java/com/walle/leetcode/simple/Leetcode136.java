package com.walle.leetcode.simple;

/*
    给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

    说明：

    你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 */
public class Leetcode136 {
    public int singleNumber(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum ^= i;// 用 异或， 两个相同的数异或为0，最后剩下来是 单个的
        }
        return sum;
    }

    public static void main(String[] args) {
        Leetcode136 lc = new Leetcode136();
        int[] input = {4, 1, 2, 1, 2};
        System.out.println(lc.singleNumber(input));
    }
}
