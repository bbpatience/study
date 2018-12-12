package com.walle.synctrl;

/**
 * @author: bbpatience
 * @date: 2018/12/3
 * @description: Daemon Thread.
 **/
public class DaemonDemo {
    public static class DaemonT extends Thread {

        public DaemonT(String name) {
            super(name);
        }

        public void run() {
            while (true) {
                System.out.println("I am alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t= new DaemonT("baibing");
        t.setDaemon(true);

        t.start();

        Thread.sleep(2000);
    }
}
