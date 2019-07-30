package com.walle.linklist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: bbpatience
 * @date: 2019/7/22
 * @description: Stack
 **/
public class Stack {
    private int top;
    private List<Integer> array;

    public Stack() {
        this.top = -1;
        this.array = new ArrayList<>();
    }

    public void push(Integer v) {
        this.array.add(v);
        top++;
    }

    public int pop() {
        return this.array.get(top--);
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(3);
        stack.push(2);
        stack.push(1);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
