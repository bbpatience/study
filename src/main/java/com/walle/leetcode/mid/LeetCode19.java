package com.walle.leetcode.mid;

import com.walle.linklist.ListNode;

/*
给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：

给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.
说明：

给定的 n 保证是有效的。
 */
public class LeetCode19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode p = head, pre = dummy;
        int count = 0;
        while (p != null) {
            ++count;
            p = p.next;
        }
        count -= n;

        p = head;
        while (count > 0) {
            --count;
            pre = p;
            p = p.next;
        }
        pre.next = p.next;

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
        LeetCode19 lc = new LeetCode19();
        int[] nums = {1, 2, 3, 4, 5};
        ListNode head = initListNode(nums);
        print(head, "Before:");
        head = lc.removeNthFromEnd(head, 5);
        print(head, "After:");
    }
}
