package com.walle.bitset;

/**
 * @author: bbpatience
 * @date: 2019/6/25
 * @description: TireTree
 **/

class TreeNode {
    private static final int MAX_CHARS = 26;
    boolean isEnd;
    char ch;
    TreeNode[] children;

    public TreeNode() {
        isEnd = false;
        children = new TreeNode[MAX_CHARS];
    }
}
public class TireTree {

    public static void buildTree(String str, TreeNode node) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int loc = chars[i] - 'a';
            if (node.children[loc] == null) {
                node.children[loc] = new TreeNode();
                node.children[loc].ch = chars[i];
            }
            node = node.children[loc];
        }
        node.isEnd = true;
    }
    
    public static boolean find(String str, TreeNode node) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int loc = chars[i] - 'a';
            if (node.children[loc] == null) {
                return false;
            }
            node = node.children[loc];
        }
        return node.isEnd;
    }

    public static void main(String[] args) {
        String[] verify = {"java", "ps", "haha", "pserid"};
        TreeNode root = new TreeNode();
        for (String str : verify) {
            buildTree(str, root);
        }

        boolean result = find("hah", root);
        System.out.println(result);
    }
}
