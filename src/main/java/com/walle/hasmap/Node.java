package com.walle.hasmap;

/**
 * @author: bbpatience
 * @date: 2019/7/25
 * @description: Node
 **/
public class Node<K, V> {
    public K key;
    public V value;
    public Node<K, V> next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}
