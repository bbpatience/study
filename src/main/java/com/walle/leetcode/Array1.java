package com.walle.leetcode;

public class Array1 {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1)
            return nums.length;
        int i = 0,j = 0;
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
        int[] list = {0,0,1,1,1,2,2,3,3,4};

        Array1 array = new Array1();
        int len = array.removeDuplicates(list);
        for (int i = 0; i < len; i++) {
            System.out.print(list[i] + " ");
        }

    }
}
