package org.myself.huawei.machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 输入整型数组和排序标识，对其元素按照升序或降序进行排序
 *
 * @author mapengfei
 */
public class HJ101 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            //获取输入元素个数
            int elementNums = in.nextInt();
            List<Integer> elements = new ArrayList<>();
            for (int i = 0; i < elementNums; i++) {
                int inputElement = in.nextInt();
                elements.add(inputElement);
            }

            //获取排序标识。0: 升序, 1: 降序
            int orderMark = in.nextInt();

            Integer[] sortArray = sort(elements, orderMark);
            for (int i = 0; i < sortArray.length; i++) {
                System.out.print(sortArray[i] + " ");
            }
        }
    }

    /**
     * 对输入的元素进行排序
     * @param elements    元素
     * @param orderMark   排序标识
     * @return 排序后的集合
     */
    public static Integer[] sort(List<Integer> elements, int orderMark) {

        Integer[] array = elements.toArray(new Integer[elements.size()]);
        switch (orderMark) {
            case 0 :
                orderByAsc(array);
                break;
            case 1 :
                orderByDesc(array);
                break;
        }

        return array;
    }

    public static Integer[] orderByAsc(Integer[] elements) {
        for (int i = 0; i < elements.length - 1; i++) {
            for (int j = i + 1; j < elements.length; j++) {
                if (elements[i] > elements[j]) {
                    int temp = elements[j];
                    elements[j] = elements[i];
                    elements[i] = temp;
                }
            }
        }

        return elements;
    }

    public static Integer[] orderByDesc(Integer[] elements) {
        for (int i = 0; i < elements.length - 1; i++) {
            for (int j = i + 1; j < elements.length; j++) {
                if (elements[i] < elements[j]) {
                    int temp = elements[j];
                    elements[j] = elements[i];
                    elements[i] = temp;
                }
            }
        }

        return elements;
    }
}
