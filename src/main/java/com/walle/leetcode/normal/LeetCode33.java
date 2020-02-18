package com.walle.leetcode.normal;

/*
假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

你可以假设数组中不存在重复的元素。

你的算法时间复杂度必须是 O(log n) 级别。
 */
public class LeetCode33 {

    /**
     * 二分法，O(log n),  找到发生变化的idx， 两次二分法
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums.length <= 0)
            return -1;
        if (nums.length == 1)
            return nums[0] == target ? 0 : -1;
        int idx = findIdx(nums, 0, nums.length - 1);
        if (idx < 0) {
            return search(nums, 0, nums.length - 1, target);
        }
        return Math.max(search(nums, 0, idx, target), search(nums, idx + 1, nums.length - 1, target));
    }

    private int search(int[] a, int start, int end, int value) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] > value) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public int findIdx(int[] nums, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[start] <= nums[mid] && nums[mid + 1] <= nums[end]) {
                return mid;
            } else if (nums[start] > nums[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LeetCode33 lc = new LeetCode33();
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
//        int[] nums = {0, 1, 2, 3, 4, 5, 6};
//        int[] nums = {3, 5, 1};
        for (int i : nums) {
            System.out.print(i + ",");
        }
        System.out.println();
        System.out.println(lc.search(nums, 0));
    }
}
