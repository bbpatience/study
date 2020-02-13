package com.walle.leetcode.simple;

import com.walle.tree.TreeNode;
import com.walle.tree.TreeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * <p>
 * 找出路径和等于给定数值的路径总数。
 * <p>
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 */
public class Leetcode501 {
    private List<Integer> result = new ArrayList<>();
    private int max = Integer.MIN_VALUE;

    public int[] findMode(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        dfs(root, map);
        int length = result.size();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = result.get(i);
        }
        return array;

    }

    public void dfs(TreeNode root, Map<Integer, Integer> map) {
        if (root == null)
            return;
        int count;
        if (map.containsKey(root.val)) {
            count = map.get(root.val);
            map.put(root.val, ++count);
        } else {
            count = 1;
            map.put(root.val, count);
        }
        if (count > max) {
            max = count;
            result.clear();
            result.add(root.val);
        } else if (count == max) {
            result.add(root.val);
        }
        dfs(root.left, map);
        dfs(root.right, map);
    }

    public static void main(String[] args) {
        Leetcode501 lc = new Leetcode501();
        Integer[] list = {1, null, 2, 2};
        TreeNode root = TreeUtils.init(list);
        for (int i : lc.findMode(root)) {
            System.out.print(i + ",");
        }
        System.out.println();
    }
}
