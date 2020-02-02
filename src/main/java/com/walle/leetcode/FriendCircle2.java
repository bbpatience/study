package com.walle.leetcode;

import java.util.Arrays;

//leetcode 547
public class FriendCircle2 {

    private int find(int[] parent, int x) {
        if (parent[x] == -1) {
            return x;
        }
        return find(parent, parent[x]);
    }
    private void union(int[] parent, int i, int j) {
        int findI = find(parent, i);
        int findJ = find(parent, j);
        if (findI == findJ)
            return;
        parent[i] = j;
    }

    public int findCircleNum(int[][] M) {
        int[] parent = new int[M.length];
        Arrays.fill(parent, -1);

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] == 1 && i != j) {
                    union(parent, i, j);
                }
            }
        }

        int count = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == -1)
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] input = new int[][]{
                {1, 0, 0, 1},
                {0, 1, 1, 0},
                {0, 1, 1, 1},
                {1, 0, 1, 1}
        };
        FriendCircle2 item = new FriendCircle2();
        System.out.println(item.findCircleNum(input));
    }
}
