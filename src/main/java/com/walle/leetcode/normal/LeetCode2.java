package com.walle.leetcode.normal;

import com.walle.linklist.ListNode;

/*
给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
public class LeetCode2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int flag = 0;

        ListNode head = new ListNode(-1);
        ListNode pre = head;
        while (l1 != null || l2 != null) {
            int sum = (l1 != null ? l1.value : 0) + (l2 != null ? l2.value : 0) + flag;
            flag = sum / 10;

            pre.next = new ListNode(sum % 10);
            pre = pre.next;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        if (flag == 1) {
            pre.next = new ListNode(1);
        }

        return head.next;
    }

    public ListNode initListNode(int[] array) {
        ListNode head = new ListNode(array[0]);
        ListNode p = head;
        for (int i = 1; i < array.length; i++) {
            ListNode tmp = new ListNode(array[i]);
            p.next = tmp;
            p = tmp;
        }
        return head;
    }

    public void print(ListNode head, String prefix) {
        head = revert(head);
        ListNode p = head;
        System.out.print(prefix);
        while (p != null) {
            System.out.print(p.value);
            p = p.next;
        }
        System.out.println();
    }

    private ListNode revert(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        ListNode cur = head;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        int[] array1 = new int[]{1, 8};
        int[] array2 = new int[]{0};
        LeetCode2 lc = new LeetCode2();
        ListNode head1 = lc.initListNode(array1);
        ListNode head2 = lc.initListNode(array2);
        ListNode resultHead = lc.addTwoNumbers(head1, head2);

        lc.print(head1, "param1:");
        lc.print(head2, "param2:");
        lc.print(resultHead, "add result:");
    }
}
