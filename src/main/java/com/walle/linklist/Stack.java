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
        this.array = new ArrayList<Integer>();
    }

    public void push(int v) {
        this.array.add(top, v);
        top++;
    }

    public int pop() {
        int v = this.array.get(top);
        top--;
        return v;
    }
}
