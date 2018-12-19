package com.walle.concurrent.deadlock;

/**
 * @author: bbpatience
 * @date: 2018/12/19
 * @description: DeadLock
 **/
public class DeadLock extends Thread {
    protected Object tool;

    static Object fork1 = new Object();
    static Object fork2 = new Object();

    public DeadLock(Object tool) {
        this.tool = tool;

        if (tool == fork1) {
            this.setName("Phi A");
        }
        if (tool == fork2) {
            this.setName("Phi B");
        }
    }

    @Override
    public void run() {
        if (tool == fork1) {
            synchronized (fork1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (fork2) {
                    System.out.println("Phi A eating");
                }
            }
        }
        if (tool == fork2) {
            synchronized (fork2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (fork1) {
                    System.out.println("Phi B eating");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadLock phiA = new DeadLock(fork1);
        DeadLock phiB = new DeadLock(fork2);
        phiA.start();
        phiB.start();

        Thread.sleep(1000);
    }
}
