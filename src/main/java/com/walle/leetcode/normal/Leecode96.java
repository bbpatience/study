package com.walle.leetcode.normal;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 */
public class Leecode96 {
    /**
     * dp[n]为结果， 它等于所以F(i)之和。
     * F(i)表示以i为根节点，能产生的二叉搜索树有多少
     * 它等于前 1~i-1个节点组成二叉树的个数  乘以  i + 1 ~n 个节点组成二叉探索树
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];
    }

    private Map<Integer, Integer> map = new HashMap<>();
    public int numTrees2(int n) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        if (n <= 1)
            return 1;
        int res = 0;
        for (int i = 1; i < n + 1; ++i) {
            res += numTrees2(i - 1) * numTrees2(n - i);
        }
        map.put(n, res);
        return res;
    }

    public static void main(String[] args) {
        Leecode96 lc = new Leecode96();
        System.out.println(lc.numTrees2(5));
    }
}
