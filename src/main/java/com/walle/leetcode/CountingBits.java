package com.walle.leetcode;

public class CountingBits {
    private static int[] counting(int n) {
        int[] result = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            result[i] = result[i & (i - 1)] + 1;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] rsp = counting(5);
        for (int i : rsp) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
