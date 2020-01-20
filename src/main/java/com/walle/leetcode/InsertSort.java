package com.walle.leetcode;

/**
 * @author: bbpatience
 * @date: 2020/1/20
 * @description: InsertSort
 **/
public class InsertSort {
    public static void insertSort(int[] a, int n) {
        if (n <= 1)
            return;
        for (int i = 1; i < n; ++i) {
            int value = a[i];
            int j = i - 1;
            for (; j >= 0; --j) {
                if (a[j] > value) {
                    a[j+1] = a[j];// 数据移动
                } else {
                    break;
                }
            }
            a[j+1] = value; //insert
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
        insertSort(array, array.length);
        System.out.print("\n sort: ");
        print(array);
    }
}
