package com.walle.leetcode.normal;

import java.util.*;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个位置。
 */
public class Leecode55 {

    public boolean canJump(int[] nums) {
        boolean[] resultList = new boolean[nums.length];

        Set<Integer> set = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int idx = stack.pop();
            if (nums[idx] + idx > nums.length)
                return true;
            resultList[idx] = true;
            for(int i = 1; i <= nums[idx]; ++i) {
                if (i + idx < nums.length) {
                    resultList[i + idx] = true;
                    if (!set.contains(i + idx)) {
                        stack.push(i + idx);
                        set.add(i+idx);
                    }
                }
            }
        }

        return resultList[nums.length - 1];
    }

    public static void main(String[] args) {
        Leecode55 lc = new Leecode55();
//        int[] list = {2, 3, 1, 1, 4};
        int[] list = {3, 2, 1, 0, 4};
        System.out.println(lc.canJump(list));
    }
}
