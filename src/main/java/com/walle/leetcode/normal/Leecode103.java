package com.walle.leetcode.normal;

import com.walle.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.walle.tree.TreeUtils.init;
import static com.walle.tree.TreeUtils.print;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 */
public class Leecode103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isOdd = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (isOdd)
                    list.addLast(node.val);
                else
                    list.addFirst(node.val);
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
                size--;
            }
            isOdd = !isOdd;
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        Leecode103 lc = new Leecode103();
        Integer[] input = {1, 2, 3, 4, null, null, 5};
        TreeNode head = init(input);
        print(head);
        List<List<Integer>> result = lc.zigzagLevelOrder(head);
        result.forEach(list -> {
            list.forEach(item -> {
                System.out.print(item + ",");
            });
            System.out.println();
        });
    }
}
