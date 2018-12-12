package com.walle.synctrl;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: bbpatience
 * @date: 2018/12/7
 * @description: CyclicBarrierDemo
 **/
public class CyclicBarrierDemo {
    public static class Soldier implements Runnable {

        private String soldierName;
        private CyclicBarrier cb;

        public Soldier(String soldierName, CyclicBarrier cb) {
            System.out.println(soldierName + " here");
            this.soldierName = soldierName;
            this.cb = cb;
        }

//        @Override
        public void run() {
            try {
                cb.await();// 等待所有士兵到齐
                doWork();
                //等待所有士兵完成工作
                cb.await();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException " + soldierName);
            } catch (BrokenBarrierException e) {
                System.out.println("BrokenBarrierException " + soldierName);
            }
        }
        private void doWork() {
            try {
                long sleep = (new Random().nextInt(9) + 1);
                System.out.println(soldierName + " sleep " + sleep);
                Thread.sleep(sleep * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldierName + " work done.");
        }
    }

    public static class BarrierRunnable implements Runnable {

        boolean flag;
        int n;

        public BarrierRunnable(boolean flag, int n) {
            this.flag = flag;
            this.n = n;
        }

//        @Override
        public void run() {
            if (flag) {
                System.out.println("Commander : [ Soldier " + n + "], tasks done.");
            } else {
                System.out.println("Commander : [ Soldier " + n + "], All together.");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final int N = 10;
        Thread[] allSoldiers = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclic = new CyclicBarrier(N, new BarrierRunnable(flag, N));
        System.out.println("Ready ?");

        for ( int i = 0; i < N ; ++i) {
            allSoldiers[i] = new Thread(new Soldier("Soldier" + i, cyclic));
            allSoldiers[i].start();

//            if (i == 5) {
//                allSoldiers[i].interrupt();
//            }
        }
    }
}
