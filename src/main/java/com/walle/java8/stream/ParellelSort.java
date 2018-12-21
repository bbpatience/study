package com.walle.java8.stream;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: bbpatience
 * @date: 2018/12/21
 * @description: ParellelSort
 **/
public class ParellelSort {

    public static void main(String[] args) {
        int[] arr = new int[100];
        Random r = new Random();

//        Arrays.setAll(arr, (i) -> r.nextInt(100));
        Arrays.parallelSetAll(arr, (i) -> r.nextInt(100));
        Arrays.stream(arr).forEach(ParellelSort::print);
        System.out.println();

        Arrays.parallelSort(arr);
        Arrays.stream(arr).forEach(ParellelSort::print);
        System.out.println();
    }

    private static void print(int x) {
        System.out.print(x + " ,");
    }
}
