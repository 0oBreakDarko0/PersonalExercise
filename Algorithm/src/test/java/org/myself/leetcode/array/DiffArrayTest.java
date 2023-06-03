package org.myself.leetcode.array;

import org.junit.Test;

public class DiffArrayTest {

    @Test
    public void testDiff() {
        int[] array = {8, 2, 6, 3, 1};

        DiffArray diffArray = new DiffArray(array);
        diffArray.increment(1, 3, 3);
        diffArray.result();
    }
}
