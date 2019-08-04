package com.walle.leetcode;

import java.util.HashSet;
import java.util.Set;

public class OnlyOnceArray {
    public int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int i = 0;
        Set<Integer> set = new HashSet<>();
        while (i < nums.length - 1) {
            if (set.contains(i)) {
                i++;
                continue;
            }
            int findIdx = findEqual(nums, i+1, nums[i]);
            if (findIdx > 0) {
                set.add(findIdx);
            } else {
                return nums[i];
            }
            i++;
        }
        return nums[nums.length - 1];

    }
    public int findEqual(int[] nums, int from, int value) {
        int i = from;
        while (i < nums.length) {
            if (nums[i] == value) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] list = {2,2, 1, 1, 5};

        OnlyOnceArray array = new OnlyOnceArray();

        System.out.println(array.singleNumber(list));
        System.out.println(array.singleNumber2(list));
    }

    public int singleNumber2(int[] nums) {
        int result = 0;
        for(int i=0; i<nums.length;i++) {
            result ^= nums[i];
        }
        return result;
    }
}
