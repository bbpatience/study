package com.walle.leetcode;

//并查集
public class UnionFind {
    public int count;
    private int[] roots;

    public UnionFind(char[][] grid) {
        count = 0;
        int m = grid.length;   // row
        int n = grid[0].length; // column
        roots = new int[m * n];
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                if (grid[i][j] == '1') {
                    roots[i * n + j] = i * n + j;
                    ++count;
                }
            }
        }
    }

    private int findRoot(int i) {
        int root = i;
        while (root != roots[root]) {
            root = roots[root];
        }

        // 优化， 扁平化树， 缩小高度
        while (i != roots[i]) {
            int tmp = roots[i];
            roots[i] = root;
            i = tmp;
        }
        return root;
    }

    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    public void union(int p, int q) {
        int qroot = findRoot(q);
        int proot = findRoot(p);
        if (qroot != proot) {
            roots[proot] = qroot;
            --count;
        }
    }
}
