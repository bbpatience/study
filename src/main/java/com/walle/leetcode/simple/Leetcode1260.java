package com.walle.leetcode.simple;

import com.walle.linklist.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 m 行 n 列的二维网格 grid 和一个整数 k。你需要将 grid 迁移 k 次。
 * <p>
 * 每次「迁移」操作将会引发下述活动：
 * <p>
 * 位于 grid[i][j] 的元素将会移动到 grid[i][j + 1]。
 * 位于 grid[i][n - 1] 的元素将会移动到 grid[i + 1][0]。
 * 位于 grid[m - 1][n - 1] 的元素将会移动到 grid[0][0]。
 * 请你返回 k 次迁移操作后最终得到的 二维网格。
 */
public class Leetcode1260 {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<List<Integer>> result = new ArrayList<>();
        int m = grid.length;
        int n = grid[0].length;

        for (int cnt = 0; cnt < k; ++cnt) {
            int tmp = grid[m - 1][n - 1];
            for (int i = m - 1; i >= 0; --i) {
                for (int j = n - 1; j > 0; --j) {
                    grid[i][j] = grid[i][j - 1];
                }
                if (i > 0) {
                    grid[i][0] = grid[i - 1][n - 1];
                } else {
                    grid[0][0] = tmp;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                list.add(grid[i][j]);
            }
            result.add(list);
        }
        return result;
    }

    public List<List<Integer>> shiftGrid2(int[][] grid, int k) {
        List<List<Integer>> result = new ArrayList<>();
        int m = grid.length;
        int n = grid[0].length;
        int N = m * n;

        ListNode head = new ListNode(-1);
        ListNode p = head;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ListNode tmp = new ListNode(grid[i][j]);
                p.next = tmp;
                p = tmp;
            }
        }
        head = head.next;
        p.next = head;
        for (int i = 0; i < N - (k % N); ++i) {
            p = head;
            head = head.next;
        }
        p.next = null;

        for (int i = 0; i < m; ++i) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                list.add(head.value);
                head = head.next;
            }
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        Leetcode1260 lc = new Leetcode1260();
        int[][] grid = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<List<Integer>> list = lc.shiftGrid2(grid, 1);
        list.forEach(sublist -> {
            sublist.forEach(item -> System.out.print(item + ","));
            System.out.println();
        });
    }
}
