package com.walle.leetcode.normal;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * <p>
 * 问总共有多少条不同的路径？
 */
public class Leetcode62 {

    //dp
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        //init
        for (int i = 0; i < m; ++i) {
            dp[i][0] = 1;
        }

        for (int i = 0; i < n; ++i) {
            dp[0][i] = 1;
        }
        // dp
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        Leetcode62 lc = new Leetcode62();
        System.out.println(lc.uniquePaths(7, 3));
    }
}
