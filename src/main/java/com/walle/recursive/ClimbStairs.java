package com.walle.recursive;

/**
 * @author: bbpatience
 * @date: 2019/8/21
 * @description: ClimbStairs
 **/
public class ClimbStairs {
    public static int climbStairs(int n) {
        if (n < 3)
            return n;

        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(5));
    }
}
