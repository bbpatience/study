package com.walle.stream;

import java.util.stream.IntStream;

/**
 * @author: bbpatience
 * @date: 2018/12/14
 * @description: PrimeUtils
 **/
public class PrimeUtils {
    public static boolean isPrime(int number) {
        int tmp = number;
        if (tmp < 2) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(tmp); i++) {
            if (tmp % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        long count = IntStream.range(1, 1000000).filter(PrimeUtils::isPrime).count();
//        long count = IntStream.range(1, 1000000).parallel().filter(PrimeUtils::isPrime).count();
        long endTime = System.currentTimeMillis();
        System.out.print(endTime - startTime + "ms ");
        System.out.println(count);
    }
}
