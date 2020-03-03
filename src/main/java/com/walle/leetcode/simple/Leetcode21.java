package com.walle.leetcode.simple;

import com.walle.linklist.ListNode;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class Leetcode21 {

    private ListNode initListNode(int[] array) {
        ListNode head = new ListNode(array[0]);
        ListNode p = head;
        for (int i = 1; i < array.length; i++) {
            p.next = new ListNode(array[i]);
            p = p.next;
        }
        return head;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode head = new ListNode(-1);
        ListNode k = head;
        while (p1 != null && p2 != null) {
            if (p1.value > p2.value) {
                k.next = p2;
                p2 = p2.next;
            } else {
                k.next = p1;
                p1 = p1.next;
            }
            k = k.next;
        }
        if (p1 == null)
            k.next = p2;
        if (p2 == null)
            k.next = p1;
        return head.next;
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
        int[] nums1 = new int[]{1, 2, 4};
        int[] nums2 = new int[]{1, 3, 4};
        Leetcode21 lc = new Leetcode21();
        ListNode l1 = lc.initListNode(nums1);
        ListNode l2 = lc.initListNode(nums2);
        ListNode result = lc.mergeTwoLists(l1, l2);
        print(result, "AFTER:");
    }
}
