package org.myself.leetcode.listnode;

public class ReverseListNode {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        reverse(head);
    }

    public static ListNode reverse(ListNode head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        ListNode last = reverse(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return last;
    }

}
