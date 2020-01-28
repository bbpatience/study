package com.walle.leetcode;

public class BitCount {
    public static int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++){
            if (n%2 != 0)
                count++;
            n = n>>1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(hammingWeight(-5));
    }
}
