package com.walle.leetcode.simple;

import com.walle.tree.TreeNode;

import static com.walle.tree.TreeUtils.init;

/*
给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明: 叶子节点是指没有子节点的节点。

 */
public class Leetcode104 {
    public int maxDepth(TreeNode root) {
        //DFS
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        Integer[] list = {9, 4, 13, null, null, 11, 15};
        TreeNode head = init(list);

        Leetcode104 lc = new Leetcode104();
        System.out.println("maxDepth:" + lc.maxDepth(head));
    }
}
