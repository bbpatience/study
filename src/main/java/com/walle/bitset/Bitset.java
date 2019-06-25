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
        containChars("hello");
        System.out.println("hello".charAt(3));
        computePrime();
    }

    public static void containChars(String str) {
        BitSet used = new BitSet();
        for (int i = 0; i < str.length(); i++)
            used.set(str.charAt(i)); // set bit for char

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int size = used.size();
        System.out.println(size);
        for (int i = 0; i < size; i++) {
            if (used.get(i)) {
                sb.append((char) i);
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    public static void computePrime() {
        BitSet sieve = new BitSet(1024);
        int size = sieve.size();
        for (int i = 2; i < size; i++)
            sieve.set(i);
        int finalBit = (int) Math.sqrt(sieve.size());

        for (int i = 2; i < finalBit; i++)
            if (sieve.get(i))
                for (int j = 2 * i; j < size; j += i) {
//                    System.out.println(j);
                    sieve.clear(j);
                }

        int counter = 0;
        for (int i = 1; i < size; i++) {
            if (sieve.get(i)) {
                System.out.printf("%5d", i);
                if (++counter % 15 == 0)
                    System.out.println();
            }
        }
        System.out.println();
    }

}
