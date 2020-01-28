package com.walle.leetcode;

public class NQueen2 {
    public static int count = 0;

    public int totalNQueens(int n) {
        DFS(0, n, 0, 0, 0);
        return count;
    }

    public void DFS(int row, int n, int col, int pie, int na) {
        if (row >= n) {
            count++;
            return;
        }
        int bits = (~(col | pie | na)) & ((1 << n) - 1); // 得到不存在col,pie,na 3个集合的 位, 每个位就是个可放Queen的位置
        while (bits > 0) {
            int p = bits & -bits;// 取得最低位的1，放Queen.
            DFS(row + 1, n, col | p, (pie | p) << 1, (na | p) >> 1);
            bits &= bits - 1; // 舍放掉 在低位的1.
        }
    }

    public static void main(String[] args) {
        NQueen2 queen = new NQueen2();
        System.out.println(queen.totalNQueens(3));
    }
}
