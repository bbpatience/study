package com.walle.concurrent.synctrl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: bbpatience
 * @date: 2018/12/7
 * @description: SemaphoreDemo
 **/
public class SemaphoreDemo implements Runnable {
    public Semaphore semaphore = new Semaphore(5);
//    @Override
    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "done.");
            semaphore.release();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        SemaphoreDemo demo = new SemaphoreDemo();
        for (int i = 0; i < 20 ; i++ ) {
            exec.submit(demo);
        }
        System.out.println(Thread.currentThread().getName() + "done.");
    }
}
