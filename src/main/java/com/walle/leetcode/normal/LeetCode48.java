package com.walle.leetcode.normal;

/*
给定一个 n × n 的二维矩阵表示一个图像。

将图像顺时针旋转 90 度。
 */
public class LeetCode48 {

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                swap(matrix, i, j);
            }
        }

        for (int i = 0; i < n; ++i) {
            reverse(matrix[i]);
        }
    }

    private void swap(int[][] matrix, int i, int j) {
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = tmp;
    }

    private void reverse(int[] m) {
        int n = m.length;
        int i = 0, j = n - 1;
        while (i < j) {
            int tmp = m[i];
            m[i] = m[j];
            m[j] = tmp;
            ++i;
            --j;
        }
    }

    public static void main(String[] args) {
        LeetCode48 lc = new LeetCode48();
//        int[][] nums = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] nums = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        lc.rotate(nums);
        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j < nums.length; ++j) {
                System.out.print(nums[i][j] + ",");
            }
            System.out.println();
        }
    }
}
