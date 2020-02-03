package com.walle.leetcode;

/* 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。

    说明:

    初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
    你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
    示例:

    输入:
    nums1 = [1,2,3,0,0,0], m = 3
    nums2 = [2,5,6],       n = 3

    输出: [1,2,2,3,5,6]
*/
public class Leetcode88 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0;
        int len = m;
        while (i < len && j < n) {
            if (nums1[i] <= nums2[j]) {
                ++i;
            } else {
                insert(nums1, i++, nums2[j++]);
                ++len;
            }
        }

        if (j < n) {
            System.arraycopy(nums2, j, nums1, i, n - j);
//            for (int k = j; k < n; ++k) {
//                nums1[i++] = nums2[k];
//            }
        }
    }

    private void insert(int[] nums, int idx, int value) {
        for (int i = nums.length - 1; i > idx; --i) {
            nums[i] = nums[i - 1];
        }
        nums[idx] = value;
    }

    public static void main(String[] args) {
        int[] input1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] input2 = new int[]{2, 5, 6};
        Leetcode88 lc = new Leetcode88();
        lc.merge(input1, 3, input2, 3);
        for (int i : input1) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }
}
