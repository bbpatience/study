package com.walle.leetcode;

/**
 * @author: bbpatience
 * @date: 2020/1/20
 * @description: QuickSort
 **/
public class QuickSort {

    public static void quickSort(int[] a, int n) {
        quickSort(a, 0 , n - 1);
    }

    private static void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int q = partition(array, left, right);
        quickSort(array, left, q);
        quickSort(array, q + 1, right);
    }

    private static int partition(int[] array, int left, int right) {
        int pivot = left;
        int i = left + 1;
        int j = right;
        while (i <= j) {
            if (array[i] < array[pivot]) {
                ++i;
            } else if (array[j] > array[pivot]) {
                --j;
            } else {
                swap(array, i, j);
                ++i;
                --j;
            }
        }
        swap(array, pivot, i - 1);
        return i - 1;
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void print(int[] array) {
        for (int value : array) {
            System.out.print(value + " ,");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        System.out.print("\n origin: ");
        print(array);
        quickSort(array, array.length);
        System.out.print("\n sort: ");
        print(array);
    }
}
