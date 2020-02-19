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
public class Leecode144 {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        preorder(root, resultList);
        return resultList;
    }
    private void preorder(TreeNode root, List<Integer> list) {
        if (root ==null)
            return;
        list.add(root.val);
        preorder(root.left, list);
        preorder(root.right, list);
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        List<Integer> resultList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            resultList.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }

        }
        return resultList;
    }

    public static void main(String[] args) {
        Leecode144 lc = new Leecode144();
        Integer[] list = {9, 4, 13, 1, 5, 11, 15};
        TreeNode head = init(list);
        print(head);
        List<Integer> result = lc.preorderTraversal2(head);
        result.forEach(item -> System.out.print(item + "  ,"));
    }
}
