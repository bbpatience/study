package com.walle.concurrent.streamline;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: bbpatience
 * @date: 2018/12/20
 * @description: Div
 **/
public class Div implements Runnable{
    public static BlockingQueue<Msg> bq = new LinkedBlockingQueue<>();
    @Override
    public void run() {
        while (true) {
            try {
                Msg msg = bq.take();
                msg.j = msg.j / 2;
                System.out.println(msg.orgStr + "=" + msg.j);
            } catch (InterruptedException e) {

            }
        }
    }
}
