package com.walle.leetcode;

import java.util.*;

public class NQueen {

    private Set<Integer> colSet = new HashSet();
    private Set<Integer> pieSet = new HashSet();
    private Set<Integer> naSet = new HashSet();

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        helper(0, new String[n], result);
        return result;
    }

    public void helper(int row, String[] board, List<List<String>> result) {
        if (row == board.length) {
            result.add(Arrays.asList(board));
            return;
        }
        for (int i = 0; i < board.length; i++) {
            int id1 = row - i + board.length;
            int id2 = 2 * board.length - row - i - 1;
            if (!colSet.contains(i) && !pieSet.contains(id1) && !naSet.contains(id2)) {
                colSet.add(i);
                pieSet.add(id1);
                naSet.add(id2);

                board[row] = genStr(board.length, i);
                helper(row + 1, board, result);

                colSet.remove(i);
                pieSet.remove(id1);
                naSet.remove(id2);
            }
        }
    }

    private String genStr(int n, int q) {
        char[] chars = new char[n];
        for (int i = 0; i < n; i++) {
            chars[i] = '.';
        }
        chars[q] = 'Q';
        return new String(chars);
    }

    public static void main(String[] args) {
        NQueen queen = new NQueen();
        List<List<String>> result = queen.solveNQueens(4);
        result.forEach((list) -> {
            list.forEach(System.out::println);
            System.out.println(" =========== ");
        });
    }


}
