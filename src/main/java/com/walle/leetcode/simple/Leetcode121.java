package com.walle.leetcode.simple;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意你不能在买入股票前卖出股票。
 *
 */
public class Leetcode121 {
    public static int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int maxProfit = 0;
        for (int i = 0; i < prices.length; ++i) {
            for (int j = i + 1; j < prices.length; ++j) {
                if (prices[j] > prices[i]) {
                    maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
                }
            }
        }
        return maxProfit;
    }

    public static int maxProfit2(int[] prices) {
        int min_price = Integer.MAX_VALUE;
        int max_profit = 0;
        for (int i = 0; i < prices.length; ++i) {
            if (prices[i] < min_price)
                min_price = prices[i];
            else if (prices[i] - min_price > max_profit)
                max_profit = prices[i] - min_price;
        }
        return max_profit;
    }

    public static int maxProfit3(int[] prices) {
        int[][] dp = new int[prices.length][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[prices.length - 1][0];
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit3(prices));

    }
}
