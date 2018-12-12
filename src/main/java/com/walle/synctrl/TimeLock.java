package com.walle.synctrl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: bbpatience
 * @date: 2018/12/4
 * @description: main.java.TimeLock
 **/
public class TimeLock implements Runnable {
    private ReentrantLock lock = new ReentrantLock();
//    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + " get lock.");
                Thread.sleep(6000);
                System.out.println(Thread.currentThread().getName() + " release lock.");
            } else {
                System.out.println(Thread.currentThread().getName() + " cannot get lock.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
//            lock.unlock();
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TimeLock lock = new TimeLock();

        Thread t1 = new Thread(lock);
        Thread t2 = new Thread(lock);

        t1.start(); t2.start();
//        t1.join(); t2.join();

        System.out.println(Thread.currentThread().getName() + " done.");
    }
}
