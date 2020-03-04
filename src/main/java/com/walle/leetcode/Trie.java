package com.walle.leetcode;

public class Trie {

    private TrieNode root;

    private class TrieNode {
        public boolean isLeaf;
        public TrieNode[] nodes = new TrieNode[26];

        public TrieNode() {
            isLeaf = false;
        }
    }
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    private void insert(TrieNode node, String word, int i) {
        int idx = word.charAt(i) - 'a';
        if (node.nodes[idx] == null) {
            node.nodes[idx] = new TrieNode();
        }
        if (i == word.length() - 1) {
            node.nodes[idx].isLeaf = true;
            return;
        }

        insert(node.nodes[idx], word, i + 1);
    }
    /** Inserts a word into the trie. */
    public void insert(String word) {
        insert(this.root, word, 0);
    }

    private boolean search(TrieNode node, String word, int i) {
        TrieNode now = node.nodes[word.charAt(i) - 'a'];
        if (now != null) {
            if (i < word.length() - 1) {
                return search(now, word, i + 1);
            } else if (now.isLeaf) {
                return i == word.length() - 1;
            }
        }
        return false;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return search(this.root, word, 0);
    }

    private boolean startsWith(TrieNode node, String word, int i) {
        TrieNode now = node.nodes[word.charAt(i) - 'a'];
        if (now != null) {
            if (i < word.length() - 1) {
                return startsWith(now, word, i + 1);
            } else {
                return notEmpty(now);
            }
        }
        return false;
    }

    private boolean notEmpty(TrieNode node) {
        for (int i = 0; i < 26; i++) {
            if (node.nodes[i] != null)
                return true;
        }
        return false;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return search(prefix)  || startsWith(root, prefix, 0);
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 true
        System.out.println(trie.search("app"));     // 返回 false
        System.out.println(trie.startsWith("app")); // 返回 true
        trie.insert("app");
        System.out.println(trie.search("app"));
        System.out.println(trie.search("apple"));
        System.out.println(trie.startsWith("app")); // 返回 true
//        trie.insert("a");
//        System.out.println(trie.search("a"));   // 返回 true
//        System.out.println(trie.startsWith("a")); // 返回 true
    }
}
