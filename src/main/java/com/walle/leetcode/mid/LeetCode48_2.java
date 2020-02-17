package com.walle.leetcode.mid;

/*
给定一个 n × n 的二维矩阵表示一个图像。

将图像顺时针旋转 90 度。
 */
public class LeetCode48_2 {

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int i = 0;
        while (n > 1) {
            int[] array = new int[n];
            for (int k = 0; k < n; ++k) {
                array[k] = matrix[i][k + i];
            }
            for (int k = n - 1; k >= 0; --k) {
                matrix[i][k + i] = matrix[n - k - 1 + i][i];
            }
            for (int k = 0; k < n; ++k) {
                matrix[k + i][i] = matrix[n - 1 + i][k + i];
            }
            for (int k = 0; k < n; ++k) {
                matrix[n - 1 + i][k + i] = matrix[n - k - 1 + i][n - 1 + i];
            }
            for (int k = n - 1; k >= 0; --k) {
                matrix[k + i][n - 1 + i] = array[k];
            }
            i++;
            n -= 2;
        }
    }

    public static void main(String[] args) {
        LeetCode48_2 lc = new LeetCode48_2();
//        int[][] nums = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        int[][] nums = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] nums = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
        lc.rotate(nums);
        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j < nums.length; ++j) {
                System.out.print(nums[i][j] + ",");
            }
            System.out.println();
        }
    }
}
