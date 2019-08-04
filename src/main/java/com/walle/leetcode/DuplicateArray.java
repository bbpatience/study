package com.walle.leetcode;

import java.util.HashSet;
import java.util.Set;

public class DuplicateArray {
    public boolean containsDuplicate(int[] nums) {
        if (nums.length <= 1) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] list = {1,2,3,4};
        DuplicateArray array = new DuplicateArray();
        System.out.print(array.containsDuplicate(list));
    }
}
