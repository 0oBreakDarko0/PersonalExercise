package org.myself.leetcode.listnode;

import java.util.ArrayList;
import java.util.List;

public class ListNode {
    /**
     * 值
     */
    private int val;
    /**
     * 下一节点
     */
    private ListNode next;

    public ListNode() {
    }

    public ListNode(int curVal) {
        val = curVal;
    }

    public ListNode(int curVal, ListNode curNext) {
        val = curVal;
        next = curNext;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        List<Integer> result = new ArrayList<>();
        ListNode head = this;
        while (head != null) {
            result.add(head.getVal());
            head = head.getNext();
        }

        return result.toString();
    }
}
