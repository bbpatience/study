package com.walle.annotation;

import com.walle.annotation.FruitColor.Color;

/**
 * @author: bbpatience
 * @date: 2019/1/4
 * @description: Apple
 **/
public class Apple {

    @FruitName("Apple")
    private String appleName;

    @FruitColor(fruitColor = Color.RED)
    private String appleColor;

    public static void main(String[] args) {
        AnnotationUtils.getFieldInfo(Apple.class);
    }
}
