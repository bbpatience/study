package com.walle.linklist;

/**
 * @author: bbpatience
 * @date: 2019/7/21
 * @description: ArrayReverse
 **/
public class ArrayReverse {

    public ListNode reverse(ListNode head) {
        ListNode newHead = null;
        while (head!=null) {
            ListNode t = head.next;
            head.next = newHead;
            newHead = head;
            head = t;
        }
        return newHead;
    }
    public ListNode initListNode(int[] array) {
        ListNode head = new ListNode(array[0]);
        ListNode p = head;
        for (int i=1; i<array.length;i++) {
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
        ArrayReverse item = new ArrayReverse();
        int[] array = new int[]{1, 2, 3, 4, 5 , 6};
        ListNode head = item.initListNode(array);
        item.print(head, "origin: ");
        item.print(item.reverse(head), "result: ");
    }
}
