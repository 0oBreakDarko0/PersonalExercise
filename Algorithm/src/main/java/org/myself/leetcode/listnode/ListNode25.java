package org.myself.leetcode.listnode;

/**
 * K个一组反转链表
 * @author mapengfei
 */
public class ListNode25 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode result = reverseKGroup(head, 2);
        System.out.println(result.toString());
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        ListNode a = head, b = head;

        for(int i = 0; i < k; i++) {
            if (b == null) {
                return head;
            }

            b = b.getNext();
        }

        ListNode newHead = reverse(a, b);
        a.setNext(reverseKGroup(b, k));
        return newHead;
    }

    public static ListNode reverse(ListNode head, ListNode tail) {
        ListNode prev, cur;
        prev = null; cur = head;

        while (cur != tail) {
            //记录后续节点
            ListNode next = cur.getNext();
            //将当前节点的后续节点置为前置节点
            cur.setNext(prev);
            prev = cur;
            cur = next;
        }

        return prev;
    }
}
