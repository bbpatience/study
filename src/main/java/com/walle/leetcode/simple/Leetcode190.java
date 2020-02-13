package com.walle.leetcode.simple;

/*
颠倒给定的 32 位无符号整数的二进制位。
 */

public class Leetcode190 {
    public static int reverseBits(int n) {
        int sum = 0;
        for (int i = 0; i < 32; ++i) {
            int mask = 1 << (31 - i);
            if ((n & mask) == mask)
                sum |= (1 << i);
//            System.out.println(Integer.toString(sum,2) );
        }
        return sum;
    }

    public static void main(String[] args) {
        int test = 43261596;
        System.out.println(reverseBits(test));
    }
}
