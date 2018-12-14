package com.walle.concurrent.synctrl;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: bbpatience
 * @date: 2018/12/4
 * @description: main.java.FairLock
 **/
public class FairLock implements Runnable {

    private ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " get lock");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLock lock = new FairLock();

        Thread t1 = new Thread(lock);
        Thread t2 = new Thread(lock);
        t1.start();
        t2.start();
    }
}
