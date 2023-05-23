package org.myself.structure;

import org.junit.Test;

public class MyHeapTest {

    @Test
    public void testMyHeap() {
        MyHeap myHeap = new MyHeap();
        myHeap.push(1);
        myHeap.push(2);
        myHeap.push(3);
        myHeap.push(4);
        myHeap.push(5);

        int size = myHeap.size();

        for(int i = 0; i < size; i++) {
            System.out.println(myHeap.pop());
        }
    }
}
