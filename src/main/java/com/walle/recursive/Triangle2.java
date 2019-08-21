package com.walle.recursive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: bbpatience
 * @date: 2019/8/21
 * @description: Triangle
 **/
public class Triangle2 {
    public static Map<Map<Integer, Integer>, Integer> cache = new HashMap<>();

    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= rowIndex + 1; i++)
            if (i == rowIndex + 1) {
                for (int j = 1; j <= i; j++)
                    list.add(func(i, j));
            }
        return list;
    }

    public int func(int i, int j) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(i, j);
        if  (cache.containsKey(map)) {
            return cache.get(map);
        }

        if (j == 1 || i == j) {
            return 1;
        }
        int result = func(i - 1, j -1) + func(i - 1, j);
        if (!cache.containsKey(map)) {
            cache.put(map, result);
        }
        return result;
    }

    public static void print(List<Integer> list) {
        list.forEach(item ->
            System.out.print(item + " ")
        );
    }

    public static void main(String[] args) {
        Triangle2 tri = new Triangle2();
        List<Integer> list = tri.getRow(3);
        Triangle2.print(list);
    }
}
