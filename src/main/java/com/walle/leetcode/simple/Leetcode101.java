package com.walle.leetcode.simple;

import com.walle.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static com.walle.tree.TreeUtils.init;
import static com.walle.tree.TreeUtils.print;

/*
给定一个二叉树，检查它是否是镜像对称的。
 */
public class Leetcode101 {

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        else if (left == null || right == null)
            return false;
        else if (left.val != right.val)
            return false;
        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

    public boolean isSymmetric2(TreeNode root) {
        List<Integer> result1 = new ArrayList<>();
        List<Integer> result2 = new ArrayList<>();
        preOrder(root, result1);
        mirror(root);
        preOrder(root, result2);
        for (int i = 0; i < result1.size(); ++i) {
            if (result1.get(i) != result2.get(i)) {
                return false;
            }
        }
        return true;
    }

    private void preOrder(TreeNode root, List<Integer> resultList) {
        if (root == null)
            return;
        resultList.add(root.val);
        preOrder(root.left, resultList);
        preOrder(root.right, resultList);
    }

    private TreeNode mirror(TreeNode root) {
        if (root == null)
            return null;
        TreeNode p = mirror(root.left);
        root.left = mirror(root.right);
        root.right = p;
        return root;
    }

    public static void main(String[] args) {
        Leetcode101 lc = new Leetcode101();
        Integer[] list = {1, 2, 2, 3, 4, 4, 3};
        TreeNode head = init(list);
        print(head);
        System.out.println(lc.isSymmetric2(head));
    }
}
