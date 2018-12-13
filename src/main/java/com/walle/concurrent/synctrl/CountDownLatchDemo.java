package com.walle.concurrent.synctrl;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: bbpatience
 * @date: 2018/12/7
 * @description: CountDownLatchDemo
 **/
public class CountDownLatchDemo implements Runnable {

    private static CountDownLatch downLatch = new CountDownLatch(10);

//    @Override
    public void run() {
        try {
            long sleep = (new Random().nextInt(9) + 1) * 1000;
            Thread.sleep(sleep);
            System.out.println(Thread.currentThread().getName() + "done in " + sleep);
            downLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatchDemo demo = new CountDownLatchDemo();
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0 ; i < 10 ; i++) {
            pool.submit(demo);
        }
        System.out.println(Thread.currentThread().getName() + " waiting");
        downLatch.await();
        System.out.println("Fire.");
    }
}
