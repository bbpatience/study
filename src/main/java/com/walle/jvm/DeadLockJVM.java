package com.walle.jvm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author: bbpatience
 * @date: 2019/8/2
 * @description: DeadLockJVM
 **/
public class DeadLockJVM {

    public static void createBusyThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    ;
                }
            }
        }, "testBusyThread");
        thread.start();
    }

    public static void createLockThread(final Object lock) {
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "testLockThread");
        thread.start();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        Object obj = new Object();
        createLockThread(obj);
    }
}
