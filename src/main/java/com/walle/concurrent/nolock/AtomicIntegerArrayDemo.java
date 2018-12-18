package com.walle.concurrent.nolock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author: bbpatience
 * @date: 2018/12/18
 * @description: AtomicIntegerArrayDemo
 **/
public class AtomicIntegerArrayDemo {

        static AtomicIntegerArray array = new AtomicIntegerArray(10);
        public static class AddThread implements Runnable {

            @Override
            public void run() {
                for (int k = 0; k < 10000; k++) {
                    array.getAndIncrement(k % array.length());
                }
            }
        }

        public static void main(String[] args) throws InterruptedException {

            Thread[] ts = new Thread[10];
            for (int j = 0; j < 10; j++) {
                ts[j] = new Thread(new AddThread());
            }
            for (int j = 0; j < 10; j++) {
                ts[j].start();
            }
            for (int j = 0; j < 10; j++) {
                ts[j].join();
            }
            System.out.println(array);
        }


}
