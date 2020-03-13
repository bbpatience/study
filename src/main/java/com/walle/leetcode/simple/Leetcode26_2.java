package com.walle.leetcode.simple;

/**
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 */
public class Leetcode26_2 {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int i = 0, j = 1;
        while (j < nums.length) {
            if (nums[i] == nums[j]) {
                ++j;
            } else {
                nums[++i] = nums[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        int[] list = {1,2,2};

        Leetcode26_2 array = new Leetcode26_2();
        int len = array.removeDuplicates(list);
        for (int i = 0; i < len; i++) {
            System.out.print(list[i] + " ");
        }

    }
}
