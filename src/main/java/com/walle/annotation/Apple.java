package com.walle.annotation;

import com.walle.annotation.FruitColor.Color;

/**
 * @author: bbpatience
 * @date: 2019/1/4
 * @description: Apple
 **/
@FruitType("apple description")
public class Apple {

    @FruitName("Apple")
    private String appleName;

    @FruitColor(fruitColor = Color.RED)
    private String appleColor;

    public static void main(String[] args) {
//        AnnotationUtils.getAnnotationInfo(Apple.class);
        Apple apple = new Apple();
        AnnotationUtils.getAnnotationInfo(apple.getClass());
    }
}
