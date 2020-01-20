package com.walle.leetcode;

/**
 * @author: bbpatience
 * @date: 2020/1/20
 * @description: BubbleSort
 **/
public class BubbleSort {

    public static void bubbleSort(int[] a, int n) {
        if (n <= 1)
            return;
        for (int i = 0; i < n; ++i) {
            boolean flag = false;
            for (int j = 0; j < n - i - 1; ++j) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }

    private static void print(int[] array) {
        for (int value : array) {
            System.out.print(value + " ,");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36,26, 27, 2, 46,4, 19, 50, 48};
        System.out.print("\n origin: ");
        print(array);
        bubbleSort(array, array.length);
        System.out.print("\n sort: ");
        print(array);
    }
}
