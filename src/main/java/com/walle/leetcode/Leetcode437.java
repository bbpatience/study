package com.walle.leetcode;

import com.walle.tree.TreeNode;
import com.walle.tree.TreeUtils;

/**
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * <p>
 * 找出路径和等于给定数值的路径总数。
 * <p>
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 */
public class Leetcode437 {
    public int pathSum(TreeNode root, int sum) {
        if (root == null)
            return 0;
        return path(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int path(TreeNode root, int sum) {
        if (root == null)
            return 0;
        int res = 0;
        if (sum == root.val) {
            res++; //这个地方不能return, 还要继续递归。
        }
        return res + path(root.left, sum - root.val) + path(root.right, sum - root.val);
    }

    public static void main(String[] args) {
        Leetcode437 lc = new Leetcode437();
        Integer[] list = {5, 3, 2, 3, -2, null, 1};
        TreeNode root = TreeUtils.init(list);
        System.out.println(lc.pathSum(root, 8));
    }
}
