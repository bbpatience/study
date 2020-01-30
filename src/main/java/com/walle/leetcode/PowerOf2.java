package com.walle.leetcode;

public class PowerOf2 {
    /**
     * 是2的次方，2进制只能有一个1。
     * n & (n - 1) 去掉位运算最后一个1， 若去掉后为0，表示只有一个1.
     */
    private static boolean isPowerOf2(int n) {
        return (n > 0 && (n & (n - 1)) == 0);
    }

    public static void main(String[] args) {
        System.out.println(isPowerOf2(1024));
    }
}
