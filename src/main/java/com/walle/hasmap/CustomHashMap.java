package com.walle.hasmap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: bbpatience
 * @date: 2019/7/25
 * @description: CustomHashMap
 **/
public class CustomHashMap<K, V> {

    private List<Node<K, V>> list;
    private int capacity;
    public CustomHashMap(int capacity) {
        this.list = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            this.list.add(i, null);
        }
        this.capacity = capacity;
    }

    public void set(K key, V val) {
        int i = key.hashCode() % capacity;
        Node p = this.list.get(i);
        if (p == null) {
            this.list.add(i, new Node<>(key, val));
        } else {
            while (p != null) {
                if (p.key.equals(key)) {
                    p.value = val;
                    return;
                } else {
                    p = p.next;
                }
            }
            p.next = new Node<>(key, val);
        }
    }

    public V delete(K key) {
        int i = key.hashCode() % capacity;
        Node p = this.list.get(i);
        Node q = p;
        while (p != null) {
            if (p.key.equals(key)) {
                if (p == q) {
                    this.list.set(i, null);
                } else {
                    q.next = p.next;
                }

                return (V) p.value;
            }
            q = p;
            p = p.next;
        }
        return null;
    }

    public V get(K key) {
        int i = key.hashCode() % capacity;
        Node p = this.list.get(i);
        while (p != null) {
            if (p.key.equals(key)) {
                return (V) p.value;
            }
            p = p.next;
        }
        return null;
    }

    public static void main(String[] args) {
        CustomHashMap<String, String> map = new CustomHashMap<>(20);
        map.set("123", "hahaha1");
        map.set("124", "hahaha2");
        map.set("125", "hahaha3");

        System.out.println(map.get("124"));
        map.delete("124");
        System.out.println(map.get("124"));

        System.out.println(map.get("125"));
        map.set("125", "hahaha4");
        System.out.println(map.get("125"));

        System.out.println(map.get("126"));
        map.set("126", "hahaha6");
        System.out.println(map.get("126"));
    }

}
