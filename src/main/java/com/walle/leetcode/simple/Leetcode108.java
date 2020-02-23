package com.walle.leetcode.simple;

import com.walle.tree.TreeNode;

import static com.walle.tree.TreeUtils.print;

/*
将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 */
public class Leetcode108 {

    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private TreeNode helper(int[] nums, int left, int right) {
        if (left > right)
            return null;
        int p = (right + left) / 2;
        TreeNode root = new TreeNode(nums[p]);
        root.left = helper(nums, left, p - 1);
        root.right = helper(nums, p + 1, right);
        return root;
    }

    public static void main(String[] args) {
        Leetcode108 lc = new Leetcode108();
        int[] list = {-10, -3, 0, 5, 9};

        TreeNode head = lc.sortedArrayToBST(list);
        print(head);
    }
}
