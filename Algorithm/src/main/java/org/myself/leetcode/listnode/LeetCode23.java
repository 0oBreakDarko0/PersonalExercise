package org.myself.leetcode.listnode;

/**
 * 合并k个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */
public class LeetCode23 {

    public static void main(String[] args) {
        ListNode[] lists = {
            new ListNode(1, new ListNode(4, new ListNode(5))),
            new ListNode(1, new ListNode(3, new ListNode(4))),
            new ListNode(2, new ListNode(6))
        };

        ListNode listNode = mergeKLists(lists);

        System.out.println(listNode);
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode result = new ListNode(), p = result;
        boolean flag = true;
        while (flag && lists.length > 0) {
            ListNode minNode = new ListNode(Integer.MAX_VALUE);
            int minIndex = -1;
            flag = false;

            for (int i = 0; i < lists.length; i++) {
                ListNode cur = lists[i];
                if(cur != null) {
                    if (cur.getVal() <= minNode.getVal()) {
                        minNode = cur;
                        minIndex = i;
                        flag = true;
                    }
                }
            }

            if(minIndex >= 0) {
                p.setNext(minNode);
                p = p.getNext();
                lists[minIndex] = lists[minIndex].getNext();
            }
        }

        return result.getNext();
    }
}
