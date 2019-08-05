package com.walle.tree;

import static com.walle.tree.TreeUtils.init;
import static com.walle.tree.TreeUtils.print;

/**
 * Recursively invoke.
 * @author: bbpatience
 * @date: 2019/7/25
 * @description: TreeRecursive
 **/
public class TreeRecursive {

    public static void main(String[] args) {
        Integer[] list = {9, 4, 13, null, null, 11, 15};
        TreeNode head = init(list);
//        System.out.println("minDepth:" + minDepth(head));
//        System.out.println("maxDepth:" + maxDepth(head));
//        System.out.println("node count :" + getNodeNumRec(head));
        print(head);
        print(convertBST(head));
    }

    public static int minDepth(TreeNode root) {
        if(root == null)
            return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }

    public static int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right))+1;
    }

    public static int getNodeNumRec(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getNodeNumRec(root.left) + getNodeNumRec(root.right) + 1;
    }

    public static TreeNode mirror(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode left = mirror(root.left);
        TreeNode right = mirror(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /*
    * 计算累加树，
    * */
    public static TreeNode convertBST(TreeNode root) {
        if (root != null) {
            dfs(root, 0);
        }
        return root;
    }

    private static int dfs(TreeNode root, int sum) {
        if (root == null) {
            return sum;
        }
        sum = dfs(root.right, sum);
        int v = root.val;
        root.val += sum;
        sum += v;
        sum = dfs(root.left, sum);
        return sum;
    }
}
