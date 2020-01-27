package com.walle.leetcode;

import com.walle.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.walle.tree.TreeUtils.init;
import static com.walle.tree.TreeUtils.print;

public class BFS {

    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        List<List<Integer>> resultList = new ArrayList<>();
        LinkedList queue = new LinkedList<TreeNode>();
        queue.add(root);
        

        while (!queue.isEmpty()) {
            int len = queue.size();
            List<Integer> tmpList = new ArrayList<>();
            int i = 0;

            while (i < len) {
                TreeNode node = (TreeNode) queue.poll();
                tmpList.add(node.val);

                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);

                i++;
            }
            resultList.add(tmpList);
        }
        return resultList;
    }

    public static void main(String[] args) {
        Integer[] list = {9, 4, 13, null, null, 11, 15};
        TreeNode head = init(list);
        print(head);
        levelOrder(head);
    }
}
