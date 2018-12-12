package com.walle.generics;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: bbpatience
 * @date: 2018/12/11
 * @description: GenericTest
 **/
public class GenericTest {
    public static void main(String[] args) {
        List<String> name = new ArrayList<>();
        List<Integer> age = new ArrayList<>();
        List<Number> number = new ArrayList<>();

        name.add("icon");
        age.add(18);
        number.add(314);

        getData(name);
        getData(age);
        getData(number);

        //getUperNumber(name);//1
        getUperNumber(age);//2
        getUperNumber(number);//3

        List<Integer> list = new ArrayList<>();

        list.add(12);

        //这里直接添加会报错
//        list.add("a");
        try {
            Class<? extends List> clazz = list.getClass();
            System.out.println(list.getClass().getClassLoader());
            Method add = clazz.getDeclaredMethod("add", Object.class);

            //但是通过反射添加，是可以的        JAVA 类型擦除，所以可以。
            add.invoke(list, "kl");

            Method get = clazz.getDeclaredMethod("get", int.class);
            Integer sss = (Integer) get.invoke(list, 0);
            System.out.println(sss);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(list);
    }

    public static void getData(List<?> data) {
        System.out.println("data :" + data.get(0));
    }

    public static void getUperNumber(List<? extends Number> data) {
        System.out.println("data :" + data.get(0));
    }
}
