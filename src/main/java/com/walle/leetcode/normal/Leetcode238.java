package com.walle.leetcode.normal;

import com.walle.linklist.ListNode;

/**
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * <p>
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 */
public class Leetcode238 {

    private ListNode initListNode(int[] array) {
        ListNode head = new ListNode(array[0]);
        ListNode p = head;
        for (int i = 1; i < array.length; i++) {
            p.next = new ListNode(array[i]);
            p = p.next;
        }
        return head;
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null)
            return null;
        ListNode p = head;
        ListNode q = head.next;
        ListNode evenStart = head.next;
        while (p != null && q != null && p.next != null && q.next != null) {
            p.next = p.next.next;
            p = p.next;
            q.next = q.next.next;
            q = q.next;
        }
        if (q != null) {
            p.next = q.next;
            if (p.next != null)
                p = p.next;
            q.next = null;
        }
        p.next = evenStart;
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
        int[] nums = new int[]{1, 2, 3, 4, 5};
        Leetcode238 lc = new Leetcode238();
        ListNode head = lc.initListNode(nums);
        print(head, "PRE:");
        ListNode result = lc.oddEvenList(head);
        print(result, "AFTER:");
    }
}
