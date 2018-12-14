package com.walle.java8;

import java.util.Arrays;

/**
 * @author: bbpatience
 * @date: 2018/12/14
 * @description: DeclarativeDemo
 **/
public class DeclarativeDemo {

    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15};
        Arrays.stream(array).map((x) -> (x + 1)).forEach(DeclarativeDemo::println);
        System.out.println();
//        Arrays.stream(array).forEach(System.out::println);
        Arrays.stream(array).forEach(DeclarativeDemo::println);
    }

    private static void println(int x) {
        System.out.print(x + " ,");
    }
}
