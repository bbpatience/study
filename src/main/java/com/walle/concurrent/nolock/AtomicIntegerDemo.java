package com.walle.concurrent.nolock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: bbpatience
 * @date: 2018/12/18
 * @description: AtomicIntegerDemo
 **/
public class AtomicIntegerDemo {

    static AtomicInteger i = new AtomicInteger();
//    static int i = 0;
    public static class AddThread implements Runnable {

        @Override
        public void run() {
//            synchronized (AtomicIntegerDemo.class) {
                for (int k = 0; k < 10000; k++) {
                i.incrementAndGet();
//                    i++;
                }
//            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
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
        System.out.println("i = " + i);
        System.out.println("time=" + (System.currentTimeMillis() - start));
    }
}
