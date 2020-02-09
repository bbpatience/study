package com.walle.leetcode;

import java.util.HashSet;

/**
 * 给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，
 * 每一个数字代表一个糖果。你需要把这些糖果平均分给一个弟弟和一个妹妹。
 * 返回妹妹可以获得的最大糖果的种类数
 */
public class Leetcode575 {
    public int distributeCandies(int[] candies) {
        HashSet<Integer> set = new HashSet<>();
        for (int candy : candies) {
            set.add(candy);
        }
        return Math.min(set.size(), candies.length / 2);
    }

    public static void main(String[] args) {
        Leetcode575 lc = new Leetcode575();
        int[] input = new int[]{1, 1, 2, 3};
        System.out.println(lc.distributeCandies(input));
    }
}
