package com.walle.sort;

/**
 * @author: bbpatience
 * @date: 2018/12/13
 * @description: BubbleSort
 **/
public class BubbleSort implements BaseSort {

    public void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j + 1];
                    array[j+1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }
}
