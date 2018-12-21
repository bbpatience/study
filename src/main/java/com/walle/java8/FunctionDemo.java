package com.walle.java8;

import java.util.function.Function;

/**
 * @author: bbpatience
 * @date: 2018/12/21
 * @description: FunctionDemo
 **/
public class FunctionDemo {

    public static void main(String[] args) {

        //Function verify.
        int num = 2;
        Function<Integer, Integer> stringConverter = (from) -> from * num;
        System.out.println(stringConverter.apply(3));
    }
}
