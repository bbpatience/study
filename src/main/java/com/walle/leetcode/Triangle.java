package com.walle.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//leetcode 120
public class Triangle {
    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null)
            return 0;

        List<Integer> dp = new ArrayList<>();
        triangle.get(triangle.size() - 1).forEach(item -> dp.add(item));

        for (int i = triangle.size() - 2; i >= 0; --i) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp.set(j, triangle.get(i).get(j) + Math.min(dp.get(j), dp.get(j + 1)));
            }
        }
        return dp.get(0);
    }

    public static int minimumTotal2(List<List<Integer>> triangle) {
        if (triangle == null)
            return 0;

        int[][] dp = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];

        //init
        for (int j = 0; j < triangle.get(triangle.size() - 1).size(); j++)
            dp[triangle.size() - 1][j] = triangle.get(triangle.size() - 1).get(j);

        // 状态定义: dp[i][j] 为从bottom到本节点的最小加和
        // 递推公式: dp[i][j]等于 他的下一层i+1, 以j为中心，左右两个节点的 dp值（即递推值）+ 本层三角的值
        // 初始值:  bottom的初始值 即为  三角的值
        for (int i = triangle.size() - 2; i >= 0; --i) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Collections.singletonList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));

        System.out.println(minimumTotal2(triangle));
    }
}
