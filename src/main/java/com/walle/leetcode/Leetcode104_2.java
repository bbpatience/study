package com.walle.leetcode;

import com.walle.tree.TreeNode;

import java.util.LinkedList;

import static com.walle.tree.TreeUtils.init;

/*
给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明: 叶子节点是指没有子节点的节点。

 */
public class Leetcode104_2 {
    public int maxDepth(TreeNode root) {
        //BFS
        if (root == null)
            return 0;
        int level = 0;
        LinkedList queue = new LinkedList<TreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int len = queue.size();
            int i = 0;
            level++;
            while (i < len) {
                TreeNode node = (TreeNode) queue.poll();
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);

                i++;
            }

        }
        return level;
    }

    public static void main(String[] args) {
        Integer[] list = {9, 4, 13, null, null, 11, 15};
        TreeNode head = init(list);

        Leetcode104_2 lc = new Leetcode104_2();
        System.out.println("maxDepth:" + lc.maxDepth(head));
    }
}
