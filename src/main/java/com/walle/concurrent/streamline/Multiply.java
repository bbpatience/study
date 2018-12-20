package com.walle.concurrent.streamline;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: bbpatience
 * @date: 2018/12/20
 * @description: Multiply
 **/
public class Multiply implements Runnable {
    public static BlockingQueue<Msg> bq = new LinkedBlockingQueue<>();

    @Override
    public void run() {
        while (true) {
            try {
                Msg msg = bq.take();
                msg.j = msg.i * msg.j;
                Div.bq.add(msg);
            } catch (InterruptedException e) {

            }
        }
    }
}
