package com.walle.bitset;

import java.util.BitSet;

/**
 * @author: bbpatience
 * @date: 2019/6/24
 * @description: Bitset
 **/
public class Bitset {
    public static void main(String[] args) {
        int [] array = new int [] {1,2,3,22,0,3, 1003, 4329,4321,123,3123,1231};
        BitSet bitSet  = new BitSet();
        //将数组内容组bitmap
        for(int i=0;i<array.length;i++) {
            bitSet.set(array[i], true);
        }
        System.out.println(bitSet.size());
        for(int i=0;i<bitSet.size();i++) {
            System.out.println(String.valueOf(i) + ":" + bitSet.get(i));
        }

    }
}
