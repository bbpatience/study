package com.walle.leetcode;

public class RotateArray {

    public void rotate(int[] nums, int k) {
        reverse(nums, 0, nums.length);
//        print(nums, "ph1: ");
        reverse(nums, 0, k);
//        print(nums, "ph2: ");
        reverse(nums, k , nums.length);
//        print(nums, "ph3: ");

    }

    public void reverse(int[] nums, int from, int end) {
        int i = 0;
        int tmp;
        while (i < (end - from)/2) {
            tmp = nums[i + from];
            nums[i + from] = nums[end - 1 - i];
            nums[end - 1 - i] = tmp;

            i++;
        }
    }

    public static void main(String[] args) {
        int[] list = {1,2,3,4,5,6,7};
        int k = 2;
        RotateArray array = new RotateArray();
        print(list, "origin: ");
//        array.reverse(list, 0, list.length);
        array.rotate(list, k);
        print(list, "result: ");

    }

    public static void print(int[] nums, String tag) {
        System.out.print(tag);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }
}
