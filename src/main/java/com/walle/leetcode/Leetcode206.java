package com.walle.leetcode;

import com.walle.linklist.ListNode;

import java.util.Stack;

/**
 * 反转一个单链表。
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
public class Leetcode206 {

    //move the pointer.
    public ListNode reverseList(ListNode head) {
        // 3 指针， pre, cur, next
        // 先找到 next 保存
        // cur.next 指向 pre.
        // pre, cur 往前移动。
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    // recursive
    public ListNode reverse2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverse2(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    // stack
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        Stack<Integer> stack = new Stack<>();
        ListNode p = head;
        while (p != null) {
            stack.push(p.value);
            p = p.next;
        }
        head = new ListNode(stack.pop());
        p = head;
        while (!stack.isEmpty()) {
            p.next = new ListNode(stack.pop());
            p = p.next;
        }
        return head;
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
        ListNode p = head;
        System.out.print(prefix);
        while (p != null) {
            System.out.print(p.value + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Leetcode206 item = new Leetcode206();
        int[] array = new int[]{1, 2, 3, 4};
        ListNode head = item.initListNode(array);
        item.print(head, "origin: ");
        item.print(item.reverseList(head), "result: ");
    }
}
