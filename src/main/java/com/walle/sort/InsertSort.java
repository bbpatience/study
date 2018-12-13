package com.walle.sort;

/**
 * @author: bbpatience
 * @date: 2018/12/13
 * @description: InsertSort
 **/
public class InsertSort implements BaseSort {

    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int preIndex = i - 1;
            int current = array[i];
            while (preIndex >= 0 && array[preIndex] > current) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
    }
}
