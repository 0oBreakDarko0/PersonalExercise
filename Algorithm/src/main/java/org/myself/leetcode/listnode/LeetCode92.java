package org.myself.leetcode.listnode;

import java.util.List;

/**
 * 反转区间内链表
 * @author mapengfei
 */
public class LeetCode92 {

    private static ListNode successor = null;

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        //reverseBetween(head, 1, 2);
        ListNode listNode = reverseBetweenRecursive(head, 2, 4);

        System.out.println(listNode);
    }

    /**
     * 使用递归方式实现
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static ListNode reverseBetweenRecursive(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseN(head, right);
        }

        head.setNext(reverseBetweenRecursive(head.getNext(), left - 1, right - 1));

        return head;
    }

    /**
     * 反转前N个链表节点
     * @param head
     * @param n
     * @return
     */
    public static ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.getNext();
            return head;
        }

        ListNode last = reverseN(head.getNext(), n-1);
        head.getNext().setNext(head);
        head.setNext(successor);

        return last;
    }


    /**
     * 自己的写法
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {

        if (head == null || head.getNext() == null) {
            return head;
        }

        //最终的结果
        ListNode result = new ListNode();
        ListNode resultP = result;
        //当前节点和起始节点的前一节点
        ListNode cur = head, startPrev = head;
        int index = 1, startPrevIndex = 0;

        while (index <= right && cur != null) {
            if (index < left - 1) {
                //起始节点的前一节点
                startPrev = startPrev.getNext();
                startPrevIndex ++;
            }

            //后续节点
            ListNode next = cur.getNext();
            if (index >= left && index <= right) {
                cur.setNext(resultP.getNext());
                resultP.setNext(cur);
            }

            index ++;
            cur = next;
        }

        ListNode endPrev = resultP;
        while (endPrev.getNext() != null) {
            endPrev = endPrev.getNext();
        }

        endPrev.setNext(cur);
        if (left == 1) {
            return result.getNext();
        }
        startPrev.setNext(result.getNext());
        return head;
    }
}
