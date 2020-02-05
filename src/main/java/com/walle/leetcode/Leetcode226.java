package com.walle.leetcode;

import com.walle.tree.TreeNode;
import com.walle.tree.TreeUtils;

import java.util.Stack;

/**
 * 翻转一棵二叉树。
 * }
 */
public class Leetcode226 {

    private TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode p = invertTree(root.right);
        TreeNode q = invertTree(root.left);
        root.left = p;
        root.right = q;
        return root;
    }

    //by stack
    private TreeNode invertTree2(TreeNode root) {
        if (root == null)
            return null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Leetcode226 lc = new Leetcode226();
        Integer[] list = {4, 2, 7, 1, 3, 6, 9};
        TreeNode root = TreeUtils.init(list);
        TreeUtils.print(root);
        TreeNode result = lc.invertTree2(root);
        TreeUtils.print(result);
    }
}
