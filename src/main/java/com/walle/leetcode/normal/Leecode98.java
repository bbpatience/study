package com.walle.leetcode.normal;

import com.walle.tree.TreeNode;

import static com.walle.tree.TreeUtils.init;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 */
public class Leecode98 {
    /**
     * 用int 升级为long， 来解决Integer.MIN_VALUE, Integer.MAX_VALUE 作为树结点输入的问题
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValid(TreeNode root, long min, long max) {
        if (root == null)
            return true;
        return min < root.val && root.val < max
                && isValid(root.left, min, root.val)
                && isValid(root.right, root.val, max);
    }

    public static void main(String[] args) {
        Leecode98 lc = new Leecode98();
        Integer[] input = {2, 1, 4, null, null, 3, 6};
        TreeNode head = init(input);
        System.out.println(lc.isValidBST(head));
    }
}
