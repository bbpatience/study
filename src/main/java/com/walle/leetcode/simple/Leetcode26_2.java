package com.walle.leetcode.simple;

//leetcode 26
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
