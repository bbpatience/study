package com.walle.leetcode;

// leetcode 188.
public class MaxProfit {
    public static int maxProfit(int k, int[] prices) {
        int len = prices.length;
        // 特判
        if (k == 0 || len < 2) {
            return 0;
        }

        /* dp[i][j][K]：
            到下标为 i 的天数为止（从 0 开始），
            到下标为 j 的交易次数（从 0 开始）,
            状态为 K 的最大利润，K = 0 表示不持股，K = 1 表示持股
         */

        int[][][] dp = new int[len][k][2];

        // 初始化：把持股的部分都设置为一个较大的负值
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < k; j++) {
                dp[i][j][1] = Integer.MIN_VALUE;
            }
        }

        // 编写正确代码的方法：对两个"基本状态转移方程"当 i - 1 和 j - 1 分别越界的时候，做特殊判断，赋值为 0 即可
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < k; j++) {
                if (i == 0) {
                    dp[i][j][1] = -prices[0];
                    dp[i][j][0] = 0;
                } else {
                    if (j == 0) {
                        dp[i][j][1] = Math.max(dp[i - 1][j][1], -prices[i]);
                    } else {
                        // 基本状态转移方程 1
                        dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                    }
                    // 基本状态转移方程 2
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                }
            }
        }
        // 说明：i、j 状态都是前缀性质的，只需返回最后一个状态
        return dp[len - 1][k - 1][0];
    }

    public static void main(String[] args) {
        int k = 2;
        int[] array = {3, 2, 6, 5, 0, 3};

        System.out.println(maxProfit(k, array));
    }
}
