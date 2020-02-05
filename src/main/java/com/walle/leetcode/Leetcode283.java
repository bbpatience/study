package com.walle.leetcode;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * }
 */
public class Leetcode283 {
    public void moveZeroes(int[] nums) {
        for (int k = 0; k < nums.length - 1; ++k) {
            int i = 0;
            while (i < nums.length - 1) {
                if (nums[i] == 0) {
                    int tmp = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = tmp;
                }
                ++i;
            }
        }
    }

    public void moveZeroes2(int[] nums) {
        //记录0 的位置， 将0划分各个区域。
        // < 第一个0 往左不用动
        // 第一个0 和 第二个0 之间， 往左移1.
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                count++;
            else if (count > 0) {
                nums[i - count] = nums[i];
                nums[i] = 0;
            }
        }
    }

    private static void print(int[] nums) {
        for (int i : nums) {
            System.out.print(i + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Leetcode283 lc = new Leetcode283();
        int[] nums = {0, 1, 0, 3, 12};
        print(nums);
        lc.moveZeroes2(nums);
        print(nums);
    }
}
