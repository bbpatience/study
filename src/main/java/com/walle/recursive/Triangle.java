package com.walle.recursive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

/**
 * @author: bbpatience
 * @date: 2019/8/21
 * @description: Triangle
 **/
public class Triangle {
    public static Map<Pair<Integer, Integer>, Integer> cache = new HashMap<>();
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i=1; i <= numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 1; j <= i; j++)
                list.add(func(i,j));
            result.add(list);
        }
        return result;
    }

    public int func(int i, int j) {
        Pair<Integer, Integer> pair = new Pair<>(i, j);
        if  (cache.containsKey(pair)) {
            return cache.get(pair);
        }

        if (j == 1 || i == j) {
            return 1;
        }
        int result = func(i - 1, j -1) + func(i - 1, j);

        if (!cache.containsKey(pair)) {
            cache.put(pair, result);
        }
        return result;
    }

    public static void print(List<List<Integer>> list) {
        list.forEach(items -> {
            items.forEach(item -> System.out.print(item + " "));
            System.out.println();
            });
    }

    public static void main(String[] args) {
        Triangle tri = new Triangle();
        List<List<Integer>> list = tri.generate(30);
        Triangle.print(list);
    }
}
