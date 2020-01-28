package com.walle.leetcode;

public class Trie2 {

    private TrieNode root;

    private class TrieNode {
        public char c;
        public boolean isWord;
        public TrieNode[] nodes = new TrieNode[26];

        public TrieNode(char ch) {
            isWord = false;
            c = ch;
        }
    }
    /** Initialize your data structure here. */
    public Trie2() {
        root = new TrieNode(' ');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char tmp = word.charAt(i);
            if (node.nodes[tmp - 'a'] == null) {
                node.nodes[tmp - 'a'] = new TrieNode(tmp);
            };
            node = node.nodes[tmp - 'a'];
        }
        node.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char tmp = word.charAt(i);
            if (node.nodes[tmp - 'a'] == null) {
                return false;
            };
            node = node.nodes[tmp - 'a'];
        }
        return node.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char tmp = prefix.charAt(i);
            if (node.nodes[tmp - 'a'] == null) {
                return false;
            };
            node = node.nodes[tmp - 'a'];
        }
        return true;
    }

    public static void main(String[] args) {
        Trie2 trie = new Trie2();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 true
        System.out.println(trie.search("app"));     // 返回 false
        System.out.println(trie.startsWith("app")); // 返回 true
        trie.insert("app");
        System.out.println(trie.search("app"));
        System.out.println(trie.search("apple"));
        System.out.println(trie.startsWith("app")); // 返回 true
    }
}
