package com.walle.leetcode.normal;

import com.walle.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.walle.tree.TreeUtils.init;
import static com.walle.tree.TreeUtils.print;

/**
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 */
public class Leecode102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while (size > 0) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
                size--;
            }
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        Leecode102 lc = new Leecode102();
        Integer[] input = {9, 4, 13, 1, 5, 11, 15};
        TreeNode head = init(input);
        print(head);
        List<List<Integer>> result = lc.levelOrder(head);
        result.forEach(list -> {
            list.forEach(item -> {
                System.out.print(item + ",");
            });
            System.out.println();
        });
    }
}
