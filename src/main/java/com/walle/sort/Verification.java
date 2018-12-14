package com.walle.sort;

/**
 * @author: bbpatience
 * @date: 2018/12/13
 * @description: Verification
 **/
public class Verification {

    private static void print(int[] array) {
        for (int i=0; i < array.length; i++) {
            System.out.print(array[i] + " ,");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36,26, 27, 2, 46,4, 19, 50, 48};
        System.out.print("\n origin: ");
        print(array);

//        BaseSort sort = new BubbleSort();
//        BaseSort sort = new SelectionSort();
//        BaseSort sort = new InsertSort();
        BaseSort sort = new QuickSort();
//        BaseSort sort = new MergeSort();
        sort.sort(array);
        System.out.print("\n sorted: ");
        print(array);
    }
}
