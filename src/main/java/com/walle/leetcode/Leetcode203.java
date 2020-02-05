package com.walle.leetcode;

import com.walle.linklist.ListNode;

/**
 * 删除链表中等于给定值 val 的所有节点。
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
public class Leetcode203 {

    private ListNode initListNode(int[] array) {
        ListNode head = new ListNode(array[0]);
        ListNode p = head;
        for (int i = 1; i < array.length; i++) {
            p.next = new ListNode(array[i]);
            p = p.next;
        }
        return head;
    }

    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.value == val) {
            head = head.next;
        }
        ListNode cur = head, pre = head;

        while (cur != null) {
            if (cur.value == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    // 哨兵模式, 使用之后可以少考虑  头节点删除情况
    public ListNode removeElements2(ListNode head, int val) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode cur = head, pre = sentinel;
        while (cur != null) {
            if (cur.value == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return sentinel.next;
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
        int[] nums = new int[]{6, 2, 6, 3, 4, 5, 6};
        Leetcode203 lc = new Leetcode203();
        ListNode head = lc.initListNode(nums);
        print(head, "PRE:");
        ListNode result = lc.removeElements2(head, 6);
        print(result, "AFTER:");
    }
}
