package com.walle.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: bbpatience
 * @date: 2019/8/1
 * @description: TreeUtils
 **/
public class TreeUtils {
    public static void print(TreeNode head) {
        List<Integer> list = new ArrayList<>();
        if (head == null) {
            System.out.println("Null TreeNode.");
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);

        int treeDepth = maxDepth(head);
        int height = 0;
        while (height < treeDepth) {
            int size = 0;
            int queueSize = queue.size();
            while (size < queueSize) {
                TreeNode p = queue.poll();
                list.add(p == null ? null : p.val);
                queue.offer(p == null ? null : p.left);
                queue.offer(p == null ? null : p.right);
                size++;
            }
            height++;
        }
        System.out.print("{");
        for (Integer item : list) {
            System.out.print(item == null ? " null, " : " " + item + ", ");
        }
        System.out.println("}");
    }

    public static TreeNode init(Integer[] list) {
        if (list == null || list.length == 0) {
            return null;
        }
        TreeNode head = new TreeNode(list[0]);
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(head);

        int i = 1;
        int height = 1;
        while (i < list.length) {
            int j = 0;
            while (j < height * 2) {
                if (list[i + j] == null) {
                    queue2.offer(null);
                } else {
                    queue2.offer(new TreeNode(list[i + j]));
                }
                j++;
            }
            int size = 0;
            int queueSize = queue1.size();
            while (size < queueSize) {
                TreeNode p = queue1.poll();
                p.left = queue2.poll();
                p.right = queue2.poll();
                size++;
                queue1.offer(p.left);
                queue1.offer(p.right);
            }
            height++;
            i += queue1.size();
        }

        return head;
    }

    private static int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right))+1;
    }
}
