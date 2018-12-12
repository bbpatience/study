package com.walle.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: bbpatience
 * @date: 2018/12/11
 * @description: ScheduledExecutorServiceDemo
 **/
public class ScheduledExecutorServiceDemo {

    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(2);

        ses.scheduleWithFixedDelay(new Runnable() {
//        ses.scheduleAtFixedRate(new Runnable() {
//            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println(System.currentTimeMillis() / 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 2, TimeUnit.SECONDS);
    }
}
