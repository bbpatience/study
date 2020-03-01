package com.walle.leetcode.normal;

import java.util.PriorityQueue;

/*
在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 */
public class Leetcode215 {
    /**
     * 快排思想
     */

    public int findKthLargest(int[] nums, int k) {
        int idx = nums.length - k;
        int loc = partition(nums, 0, nums.length - 1);
        while (loc != idx) {
            if (loc < idx) {
                loc = partition(nums, loc + 1, nums.length - 1);
            } else {
                loc = partition(nums, 0, loc - 1);
            }
        }
        return nums[loc];
    }

    private int partition(int[] array, int left, int right) {
        int pivot = left;
        int i = left + 1;
        int j = right;
        while (i <= j) {
            if (array[i] < array[pivot]) {
                ++i;
            } else if (array[j] > array[pivot]) {
                --j;
            } else {
                swap(array, i, j);
                ++i;
                --j;
            }
        }
        swap(array, pivot, i - 1);
        return i - 1;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        Leetcode215 lc = new Leetcode215();
        int[] input = {3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println(lc.findKthLargest2(input, k));
    }

    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
        for (int i : nums) {
            if (heap.size() < k) {
                heap.add(i);
            } else if (i > heap.peek()) {
                heap.poll();
                heap.add(i);
            }
        }
        return heap.peek();
    }
}
