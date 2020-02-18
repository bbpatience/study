package com.walle.leetcode.normal;

import com.walle.linklist.ListNode;

/*
给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 */
public class LeetCode24 {

    /*
     逆归
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode p = head.next;
        ListNode q = head.next.next;
        p.next = head;
        head.next = q;
        head = p;

        head.next.next = swapPairs(head.next.next);
        return head;
    }

    /*
     */
    public ListNode swapPairs2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode p = dummy;
        while (p.next != null && p.next.next != null) {
            ListNode p1 = p.next;
            ListNode p2 = p.next.next;
            p1.next = p2.next;
            p2.next = p1;
            p.next = p2;

            p = p.next.next;
        }

        return dummy.next;
    }

    private static ListNode initListNode(int[] array) {
        ListNode head = new ListNode(array[0]);
        ListNode p = head;
        for (int i = 1; i < array.length; i++) {
            p.next = new ListNode(array[i]);
            p = p.next;
        }
        return head;
    }

    private static void print(ListNode head, String prefix) {
        ListNode p = head;
        System.out.print(prefix);
        while (p != null) {
            System.out.print(p.value + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LeetCode24 lc = new LeetCode24();
        int[] nums = {1, 2, 3, 4, 5};
        ListNode head = initListNode(nums);
        print(head, "Before:");
        head = lc.swapPairs2(head);
        print(head, "After:");
    }
}
