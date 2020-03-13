package com.walle.leetcode.simple;

/**
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 */
public class LeetCode26 {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1)
            return nums.length;
        int i = 0, j = 0;
        while (j < nums.length) {
            nums[i] = nums[j];

            j = findNextNotEqual(nums, j, nums[i]);

            i++;
        }
        return i;

    }

    public int findNextNotEqual(int[] nums, int from, int value) {
        int i = from;
        while (i < nums.length) {
            if (nums[i] != value) {
                return i;
            }
            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        int[] list = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

        LeetCode26 array = new LeetCode26();
        int len = array.removeDuplicates(list);
        for (int i = 0; i < len; i++) {
            System.out.print(list[i] + " ");
        }

    }
}
