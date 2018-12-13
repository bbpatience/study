package com.walle.concurrent.generics;

/**
 * @author: bbpatience
 * @date: 2018/12/11
 * @description: GenericMethodTest
 **/
public class GenericMethodTest {
    public static <E> E printArray(E[] inputArray) {
        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
        return inputArray[0];
    }

    public static <T extends Comparable<T>> T maximum(T x, T y, T z) {
        T max = x;
        if (y.compareTo( max ) > 0) {
            max = y;
        }
        if (z.compareTo( max ) > 0) {
            max = z;
        }
        return max;
    }
    public static void main( String args[] ) {
        Integer[] intArray = { 1, 2, 3, 4, 5 };
        Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
        Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };

        System.out.println("整型数组元素为:");
        Integer s = printArray(intArray);
        System.out.println(s);

        System.out.println("双精度型数组元素为:");
        printArray(doubleArray);

        System.out.println("字符型数组元素为:");
        printArray(charArray);

        System.out.println(String.format("%d, %d 和 %d 中最大的数为 %d\n\n",
            3, 4, 5, maximum( 3, 4, 5 )));

        System.out.println(String.format("%.1f, %.1f 和 %.1f 中最大的数为 %.1f\n\n",
            6.6, 8.8, 7.7, maximum( 6.6, 8.8, 7.7 )));

        System.out.println(String.format("%s, %s 和 %s 中最大的数为 %s\n","pear",
            "apple", "orange", maximum( "pear", "apple", "orange" )));
    }
}
