package com.walle.leetcode;

import java.util.LinkedList;

//leetcode 200  BFS
public class NumOfIslands2 {
    public class Dual {
        public int first;
        public int second;

        public Dual(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public int numIslands(char[][] grid) {
        int count = 0;
        int m = grid.length;   // row
        int n = grid[0].length; // column

        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                if (grid[i][j] == '1') {
                    count++;
                    BFS(grid, i, j, m, n);
                }
            }
        }
        return count;
    }

    private void BFS(char[][] grid, int i, int j, int m, int n) {
        LinkedList queue = new LinkedList<Dual>();
        queue.add(new Dual(i, j));

        while (!queue.isEmpty()) {
            Dual node = (Dual) queue.poll();
            if (!isValid(grid, node.first, node.second, m, n)) {
                grid[node.first][node.second] = '0';
                queue.add(new Dual(node.first + 1, node.second));
                queue.add(new Dual(node.first - 1, node.second));
                queue.add(new Dual(node.first, node.second + 1));
                queue.add(new Dual(node.first, node.second - 1));
            }
        }
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
        NumOfIslands2 item = new NumOfIslands2();
        System.out.println(item.numIslands(input));
    }
}
