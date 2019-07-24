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
//        BaseSort sort = new QuickSort();
//        BaseSort sort = new MergeSort();
//        sort.sort(array);
//        int idx = partition(array, 0, array.length - 1);
//        partition(array, 0, idx);
//        partition(array, idx + 1, array.length - 1);
        quickSort(array, 0, array.length - 1);
        System.out.print("\n sorted: ");
        print(array);
    }

    private static void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(array, left, right);
            quickSort(array, left, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, right );
        }
    }
    public static int partition(int[] array, int start, int end) {
//        print1(array,start,end);
        int i = start + 1;
        int j = end;
        int pivot = start;
        while (i <= j) {
            if (array[i] < array[pivot]) {
                i++;
            } else {
                if (array[j] > array[pivot]) {
                    j--;
                } else {
                    swap(array, i, j);
                    i++;
                    j--;
                }
            }
        }
        swap(array, pivot, i - 1);
        return i - 1;
    }
    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
//        print(array);
    }
}
