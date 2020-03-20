package com.walle.leetcode.normal;

/**
 * 顺时增加1，打印数组
 */
public class Vungle {

    public static int[][] spiralOrder(int m, int n) {
        int[][] matrix = new int[m][n];
        int count = 1;
        int r1 = 0, r2 = m - 1;
        int c1 = 0, c2 = n - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) {
                matrix[r1][c] = count++;
            }
            for (int r = r1 + 1; r <= r2; r++) {
                matrix[r][c2] = count++;
            }
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) {
                    matrix[r2][c] = count++;
                }
                for (int r = r2; r > r1; r--) {
                    matrix[r][c1] = count++;
                }
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return matrix;
    }

    public static void main(String[] args) {
        int m = 5, n = 4;
        int[][] matrix = spiralOrder(m, n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
