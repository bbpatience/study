package com.walle.leetcode.simple;

import java.util.PriorityQueue;

/**
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 * <p>
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 */
public class LeetCode40 {

    public int[] getLeastNumbers(int[] arr, int k) {
        if (k <= 0) {
            return new int[0];
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (n1, n2) -> n2 - n1);
        for (int i = 0; i < arr.length; ++i) {
            if (queue.size() < k) {
                queue.offer(arr[i]);
            } else if (arr[i] < queue.peek()) {
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        int[] resultArray = new int[queue.size()];
        int i = 0;
        while (queue.size() > 0) {
            resultArray[i++] = queue.poll();
        }
        return resultArray;
    }

    public static void main(String[] args) {
        int[] list = {3, 2, 1};

        LeetCode40 array = new LeetCode40();
        int[] result = array.getLeastNumbers(list, 2);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }

    }
}
