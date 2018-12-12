package com.walle.synctrl;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: bbpatience
 * @date: 2018/12/7
 * @description: ReenterLockCondition
 **/
public class ReenterLockCondition implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();
    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println("Thread start.");
            condition.await();
            System.out.println("Thread is going on.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockCondition rlc = new ReenterLockCondition();
        Thread t1 = new Thread(rlc);
        t1.start();

        System.out.println("main waiting 2s.");
        Thread.sleep(2000);

        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
