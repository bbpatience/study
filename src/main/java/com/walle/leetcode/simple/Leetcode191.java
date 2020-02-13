package com.walle.leetcode.simple;

/*
编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
 */

public class Leetcode191 {
    public static int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1)
                count++;
            n = n >> 1;
        }
        return count;
    }

    public static int hammingWeight2(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n &= n - 1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(hammingWeight(8));
        int n = 14;
        if ((n & 1) == 1) {
            System.out.println("奇");
        } else {
            System.out.println("偶");
        }
        /**
         * n & 1 == 1 判断奇偶
         * n & (n-1) 打掉最低位 1
         * n & ~n 得到最低位1
         */
        System.out.println(Integer.toString(n,2) );
        n &= (n-1);
        System.out.println(Integer.toString(n,2) );
        n = 14;
        System.out.println(Integer.toString(n,2) );
        n &= -n;
        System.out.println(Integer.toString(n,2) );
    }
}
