package com.walle.concurrent.producer;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author: bbpatience
 * @date: 2018/12/12
 * @description: Consumer
 **/
public class Consumer implements Runnable {
    private BlockingQueue<PCData> queue;
    private final static  int SLEEPTIME = 1000;

    public Consumer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    public void run() {
        Random r = new Random();
        System.out.println("start consumer id =" + Thread.currentThread().getId());

        try {
            while (true) {
                PCData data = queue.take();
                if (data != null) {
                    int re = data.getIntData() * data.getIntData();
                    System.out.println(MessageFormat.format("{0} * {1} = {2}",
                        data.getIntData(), data.getIntData(), re));
                    Thread.sleep(r.nextInt(SLEEPTIME));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
