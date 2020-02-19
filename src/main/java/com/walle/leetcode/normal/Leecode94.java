package com.walle.leetcode.normal;

import com.walle.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.walle.tree.TreeUtils.init;
import static com.walle.tree.TreeUtils.print;

/**
 * 给定一个二叉树，返回它的 前序 遍历, 非递归
 */
public class Leecode94 {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        inorder(root, resultList);
        return resultList;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    public static void main(String[] args) {
        Leecode94 lc = new Leecode94();
        Integer[] list = {9, 4, 13, 1, 5, 11, 15};
        TreeNode head = init(list);
        print(head);
        List<Integer> result = lc.inorderTraversal2(head);
        result.forEach(item -> System.out.print(item + "  ,"));
    }
}
