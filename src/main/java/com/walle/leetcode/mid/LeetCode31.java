package com.walle.leetcode.mid;

import java.util.Arrays;

/*
实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须原地修改，只允许使用额外常数空间。

以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1。
 */
public class LeetCode31 {

    public void nextPermutation(int[] nums) {
        // 从后边查， 找到第一个开始降序的index.
        int j = nums.length - 1;
        while (j > 0) {
            if (nums[j] <= nums[j - 1]) {
                --j;
            } else
                break;
        }
        if (j == 0) {
            //没有则重排返回
            Arrays.sort(nums);
            return;
        }
        j -= 1;// 指向前一个
        int i = nums.length - 1;
        //找第一个比nums[j]大的
        while (i > j) {
            if (nums[i] > nums[j])
                break;
            else
                --i;
        }
        //交换
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
        //最尾端升序是最小。
        Arrays.sort(nums, j + 1, nums.length);
    }

    public static void main(String[] args) {
        LeetCode31 lc = new LeetCode31();
//        int[] nums = {1,2,3,5,4};
        int[] nums = {2, 3, 1};
//        int[] nums = {5, 4, 3, 2, 1};
        for (int i : nums) {
            System.out.print(i + ",");
        }
        System.out.println();
        lc.nextPermutation(nums);
        for (int i : nums) {
            System.out.print(i + ",");
        }
        System.out.println();
    }
}
