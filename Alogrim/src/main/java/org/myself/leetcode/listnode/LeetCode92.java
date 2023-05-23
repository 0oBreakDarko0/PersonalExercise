package org.myself.leetcode.listnode;

public class LeetCode92 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        reverseBetween(head, 2, 4);

    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode p = head, prev = head, curPrev = head;

        int index = 1;
        int prevIndex = left - 1;
        while(p != null) {

            if (index < prevIndex) {
                prev = prev.getNext();
            }

            //当前节点
            ListNode cur = p;
            ListNode curNext = p.getNext();
            //获取反转区间起始节点的前一节点
            ListNode startPrev = prev.getNext();

            if(left < index && index <= right) {
                //当前节点的下一节点
                curPrev.setNext(curNext);
                prev.setNext(cur);
                cur.setNext(startPrev);
            }

            if (index > right) {
                break;
            }

            index ++;
            p = curNext;
            curPrev = cur;
        }

        return head;
    }
}
