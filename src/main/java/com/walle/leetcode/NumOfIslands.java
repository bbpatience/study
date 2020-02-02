package com.walle.leetcode;

//leetcode 200  DFS
public class NumOfIslands {
    public int numIslands(char[][] grid) {
        int count = 0;
        int m = grid.length;   // row
        int n = grid[0].length; // column

        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                if (grid[i][j] == '1')
                    count++;
                DFS(grid, i, j, m, n);
            }
        }
        return count;
    }

    private void DFS(char[][] grid, int i, int j, int m, int n) {
        if (isValid(grid, i, j, m, n)) {
            return;
        }
        grid[i][j] = '0';
        DFS(grid, i + 1, j, m, n);
        DFS(grid, i, j + 1, m, n);
        DFS(grid, i - 1, j, m, n);
        DFS(grid, i, j - 1, m, n);
    }

    private boolean isValid(char[][] grid, int i, int j, int m, int n) {
        return i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0';
    }

    public static void main(String[] args) {
        char[][] input = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        NumOfIslands item = new NumOfIslands();
        System.out.println(item.numIslands(input));
    }
}
