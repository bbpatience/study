package com.walle.sort;

/**
 * @author: bbpatience
 * @date: 2018/12/13
 * @description: SelectionSort
 **/
public class SelectionSort implements BaseSort {

    public void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min_index = i;
            // find index
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min_index]) {
                    min_index = j;
                }
            }
            // switch.
            if (min_index > i) {
                int tmp = array[min_index];
                array[min_index] = array[i];
                array[i] = tmp;
            }
        }
    }
}
