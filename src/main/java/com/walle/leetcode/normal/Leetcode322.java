package com.walle.leetcode.normal;

import java.util.Arrays;

public class Leetcode322 {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; ++i) {
            for (int j = 0; j < coins.length; ++j) {
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        Leetcode322 lc = new Leetcode322();
        boolean[] dp = new boolean[3];
        int[] coins = {1, 2, 5};
        System.out.println(lc.coinChange(coins, 11));
    }
}
