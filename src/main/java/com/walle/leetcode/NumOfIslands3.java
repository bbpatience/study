package com.walle.leetcode;

//leetcode 200  Union Find
public class NumOfIslands3 {
    public int numIslands(char[][] grid) {
        int m = grid.length;   // row
        int n = grid[0].length; // column

        UnionFind uf = new UnionFind(grid);
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                if (grid[i][j] == '0')
                    continue;
                if (isValid(grid, i, j + 1, m, n))
                    uf.union(i * n + j, i * n + (j + 1));

                if (isValid(grid, i + 1, j, m, n))
                    uf.union(i * n + j, (i + 1) * n + j);

                if (isValid(grid, i, j - 1, m, n))
                    uf.union(i * n + j, i * n + j - 1);

                if (isValid(grid, i - 1, j, m, n))
                    uf.union(i * n + j, (i - 1) * n + j);
            }
        }
        return uf.count;
    }


    private boolean isValid(char[][] grid, int i, int j, int m, int n) {
        return i >= 0 && i < m && j >= 0 && j < n && grid[i][j] != '0';
    }

    public static void main(String[] args) {
        char[][] input = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        NumOfIslands3 item = new NumOfIslands3();
        System.out.println(item.numIslands(input));
    }
}
