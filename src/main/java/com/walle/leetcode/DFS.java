package com.walle.leetcode;

import com.walle.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.walle.tree.TreeUtils.init;
import static com.walle.tree.TreeUtils.print;

public class DFS {

    private static List<List<Integer>> resultList = new ArrayList<>();
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return resultList;
        helper(root, 0);
        return resultList;
    }

    private static void helper(TreeNode node, int level) {
        if (node == null)
            return;
        if (resultList.size() == level) {
            resultList.add(new ArrayList<Integer>());
        }
        resultList.get(level).add(node.val);
        helper(node.left, level + 1);
        helper(node.right, level + 1);
    }

    public static void main(String[] args) {
        Integer[] list = {9, 4, 13, null, null, 11, 15};
        TreeNode head = init(list);
        print(head);
        levelOrder2(head);
    }

    public static void levelOrder2(TreeNode root) {
        helper2(root, 0);
    }

    private static void helper2(TreeNode node, int level) {
        if (node == null)
            return;
        System.out.println(node.val + " " + level);
        helper2(node.left, level + 1);

        helper2(node.right, level + 1);

    }
}
