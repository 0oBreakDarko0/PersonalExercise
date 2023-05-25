package org.myself.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用数组实现堆
 * 堆可以看作是完全二叉树
 * @author mapengfei
 */
public class MyHeap {
    private List<Integer> myHeap;

    public MyHeap() {
        this.myHeap = new ArrayList<Integer>();
    }

    /**
     * 添加元素
     * @param val 元素值
     */
    public void push(int val) {
        myHeap.add(val);
        siftUp(size() - 1);
    }

    /**
     * 堆顶元素出堆
     */
    public int pop() {
        //将堆顶元素与堆底元素进行交换
        swap(0, size() - 1);
        //然后移除堆底元素
        int val = myHeap.remove(size() - 1);
        // 再从顶至底进行堆化处理

        siftDown(0);
        return val;
    }

    /**
     * 加入新节点后，重新维护堆
     * @param index 当前底部靠右节点的下标值
     */
    private void siftUp(int index) {
        for (;;) {
            //当前节点的值
            int cur = myHeap.get(index);
            //获取当前节点的父节点
            int parentIndex = parent(index);
            int parent = myHeap.get(parentIndex);
            //如果小于等于父节点值，则跳出循环
            if (cur <= parent) {
                break;
            }
            //如果当前节点值大于父节点，进行交换
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    /**
     * 移除元素后从顶向下进行堆化
     * @param index 节点下标
     */
    private void siftDown(int index) {
        for (;;) {

            //获取左右节点的值
            int right = right(index);
            int left = left(index);

            int max = index;

            if (left < size()) {
                int leftVal = myHeap.get(left);
                max = myHeap.get(max) >= leftVal ? max : left;
            }

            if (right < size()) {
                int rightVal = myHeap.get(right);
                max = myHeap.get(max) >= rightVal ? max : right;
            }

            if (max == index) {
                break;
            }
            swap(index, max);
            index = max;
        }
    }

    /**
     * 交换两个节点的值
     * @param source 原节点下标
     * @param target 目标节点下标
     */
    private void swap(int source, int target) {
        int tempSource = myHeap.get(source);
        int tempTarget = myHeap.get(target);

        myHeap.set(source, tempTarget);
        myHeap.set(target, tempSource);
    }

    /**
     * 获取堆中元素数量
     * @return 堆的大小
     */
    public int size() {
        return myHeap.size();
    }
    /**
     * 获取堆顶元素
     * @return 堆顶元素
     */
    public int peek() {
        return myHeap.get(0);
    }

    /**
     * 获取当前节点的左节点
     * @param i 当前节点下标
     * @return 左节点下标
     */
    public int left(int i) {
        return 2 * i + 1;
    }

    /**
     * 获取当前节点的右节点
     * @param i 当前节点下标
     * @return 右节点下标
     */
    public int right(int i) {
        return 2 * i + 2;
    }

    /**
     * 获取当前节点的父节点
     * @param i 当前节点下标
     * @return 父节点下标
     */
    public int parent(int i) {
        return (i - 1) / 2;
    }
}
