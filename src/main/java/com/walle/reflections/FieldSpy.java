package com.walle.reflections;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author: bbpatience
 * @date: 2019/12/20
 * @description: FieldSpy
 **/

public class FieldSpy<T> {
    public boolean[][] b = {{ false, false }, { true, true } };
    public int[][] i = {{ 1, 1 }, { 2, 2 } };
    public String name  = "Alice";
    public List<Integer> list;
    public T val;
    private Integer h;

    public static void main(String... args) {
        try {
            Class<?> c = Class.forName(args[0]);
            Field f = c.getField(args[1]);
            System.out.format("Type: %s%n", f.getType());
            System.out.format("GenericType: %s%n", f.getGenericType());

            // production code should handle these exceptions more gracefully
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        } catch (NoSuchFieldException x) {
            x.printStackTrace();
        }
    }
}


