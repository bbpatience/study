package com.walle.leetcode;

import java.util.PriorityQueue;

public class KthLargest {
    private PriorityQueue<Integer> queue;
    private int k;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        queue = new PriorityQueue<>(k, (n1, n2) -> n2 - n1);
        for (int a : nums) {
            add(a);
        }
    }

    public int add(int val) {
        if (queue.size() < k) {
            queue.offer(val);
        }
        else if (val < queue.peek()) {
            queue.poll();
            queue.offer(val);
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        int k = 2;
        int[] arr = new int[]{};
        KthLargest kthLargest = new KthLargest(k, arr);
        System.out.println(kthLargest.add(3)); // returns 4
        System.out.println(kthLargest.add(5));// returns 5
        System.out.println(kthLargest.add(10));// returns 5
        System.out.println(kthLargest.add(9));// returns 8
        System.out.println(kthLargest.add(4));// returns 8
    }
}
