package com.walle.annotation;

import java.lang.reflect.Field;

/**
 * @author: bbpatience
 * @date: 2019/1/4
 * @description: AnnotationUtils
 **/
public class AnnotationUtils {

    public static void getFieldInfo(Class<?> clazz) {
        Field fields[] = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(FruitColor.class)) {
                FruitColor color = field.getAnnotation(FruitColor.class);
                System.out.println(color.fruitColor());
            }
            if (field.isAnnotationPresent(FruitName.class)) {
                FruitName name = field.getAnnotation(FruitName.class);
                System.out.println(name.value());
            }
        }
    }
}
