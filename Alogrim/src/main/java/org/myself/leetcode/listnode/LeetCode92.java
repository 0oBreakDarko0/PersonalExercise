package org.myself.leetcode.listnode;

/**
 * 反转区间内链表
 */
public class LeetCode92 {

    public static void main(String[] args) {
        ListNode head = new ListNode(3, new ListNode(5));
        reverseBetween(head, 1, 2);

    }

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
