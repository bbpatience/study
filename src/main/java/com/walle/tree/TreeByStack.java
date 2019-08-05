package com.walle.tree;

import static com.walle.tree.TreeUtils.init;
import static com.walle.tree.TreeUtils.print;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: bbpatience
 * @date: 2019/8/1
 * @description: TreeByStack
 **/
public class TreeByStack {

    public static void main(String[] args) {
        Integer[] list = {9, 4, 13, null, null, 11, 15};
        TreeNode head = init(list);
        print(head);

        for(int i: preOrder(head)) {
            System.out.print(i + " ");
        }
        System.out.println();
        recursivePreOrder(head);
    }

    private static List<Integer> preOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            list.add(node.val);

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return list;
    }

//    private static void mirror(TreeNode root) {
//        Stack<TreeNode> stack = new Stack<>();
//
//        stack.push(root);
//        while (!stack.empty()) {
//            TreeNode node = stack.pop();
//            if (node.right != null) {
//                node.left =
//            }
//
//            if (node.right != null) {
//                stack.push(node.right);
//            }
//            if (node.left != null) {
//                stack.push(node.left);
//            }
//        }
//    }

    private static void recursivePreOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            recursivePreOrder(root.left);
            recursivePreOrder(root.right);
        }
    }
}
