package com.walle.sort;

/**
 * @author: bbpatience
 * @date: 2018/12/13
 * @description: MergeSort
 **/
public class MergeSort implements BaseSort {

    @Override
    public void sort(int[] array) {
        array = mergeSort(array);
    }

    private int[] mergeSort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int middle = array.length / 2;
        int[] left_array = slice(array, 0, middle);
        int[] right_array = slice(array, middle +1, array.length -1);
        return merge(mergeSort(left_array), mergeSort(right_array));
    }

    private int[] slice(int[] array, int start, int end) {
        int[] result = new int[end - start + 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = array[i + start];
        }
        return result;
    }

    private int[] merge(int[] left, int[] right) {

        int[] result = new int[left.length + right.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (j < right.length && i < left.length) {
            if (left[i] <= right[j]) {
                 result[k] = left[i];
                 k++;
                 i++;
            } else {
                result[k] = right[j];
                j++;
                k++;
            }
        }
        if (j < right.length) {
            copy(result, k, right, j, right.length);
        }
        if (i < left.length) {
            copy(result, k, left, i, left.length);
        }
        return result;
    }

    private void copy(int[] array, int init, int[] orig, int start, int end) {
        int i = init;
        for (int j = start; j < end; i++, j++) {
            array[i] = orig[j];
        }
    }
}
