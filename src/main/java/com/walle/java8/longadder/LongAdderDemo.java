package com.walle.java8.longadder;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author: bbpatience
 * @date: 2018/12/21
 * @description: LongAdderDemo
 **/
public class LongAdderDemo {
    private static final int MAX_THREADS = 3;
    private static final int TASK_COUNT = 3;
    private static final int TARGET_COUNT = 10000000;

    private AtomicLong account = new AtomicLong(0L);
    private LongAdder laccount = new LongAdder();
    private long count = 0;

    static CountDownLatch cdlSync = new CountDownLatch(TASK_COUNT);
    static CountDownLatch cdlAtomic = new CountDownLatch(TASK_COUNT);
    static CountDownLatch cdlAdder = new CountDownLatch(TASK_COUNT);

    protected synchronized long inc() {
        return ++count;
    }

    protected synchronized  long getCount() {
        return count;
    }

    public class SyncThread implements Runnable {
        protected String name;
        protected long startTime;
        LongAdderDemo out;

        public SyncThread(long startTime, LongAdderDemo out) {
            this.startTime = startTime;
            this.out = out;
        }

        @Override
        public void run() {
            long v = out.getCount();
            while (v < TARGET_COUNT) {
                v = out.inc();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("SyncThread spend:" + (endTime - startTime) + "ms v=" + v);
            cdlSync.countDown();
        }
    }

    public void testSync() throws InterruptedException {
        ExecutorService tp = Executors.newFixedThreadPool(MAX_THREADS);
        long startTime = System.currentTimeMillis();
        SyncThread sync = new SyncThread(startTime, this);

        for (int i =0; i < TASK_COUNT; i++) {
            tp.submit(sync);
        }
        cdlSync.await();
        tp.shutdown();
    }

    public class AtomicThread implements Runnable {
        protected String name;
        protected long startTime;

        public AtomicThread(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void run() {
            long v = account.get();
            while (v < TARGET_COUNT) {
                v = account.incrementAndGet();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("AtomicThread spend:" + (endTime - startTime) + "ms v=" + v);
            cdlAtomic.countDown();
        }
    }

    public void testAtomic() throws InterruptedException {
        ExecutorService tp = Executors.newFixedThreadPool(MAX_THREADS);
        long startTime = System.currentTimeMillis();
        AtomicThread atomicThread = new AtomicThread(startTime);

        for (int i =0; i < TASK_COUNT; i++) {
            tp.submit(atomicThread);
        }
        cdlAtomic.await();
        tp.shutdown();
    }

    public class LongAdderThread implements Runnable {
        protected String name;
        protected long startTime;

        public LongAdderThread(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void run() {
            long v = laccount.sum();
            while (v < TARGET_COUNT) {
                laccount.increment();
                v = laccount.sum();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("LongAdderThread spend:" + (endTime - startTime) + "ms v=" + v);
            cdlAdder.countDown();
        }
    }

    public void testAdder() throws InterruptedException {
        ExecutorService tp = Executors.newFixedThreadPool(MAX_THREADS);
        long startTime = System.currentTimeMillis();
        LongAdderThread adderThread = new LongAdderThread(startTime);

        for (int i =0; i < TASK_COUNT; i++) {
            tp.submit(adderThread);
        }
        cdlAdder.await();
        tp.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        LongAdderDemo demo = new LongAdderDemo();
        demo.testSync();
        demo.testAtomic();
        demo.testAdder();
    }
}
