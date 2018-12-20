package com.walle.concurrent.streamline;

/**
 * @author: bbpatience
 * @date: 2018/12/20
 * @description: StreamLineMain
 **/
public class StreamLineMain {

    public static void main(String[] args) {
        new Thread(new Plus()).start();
        new Thread(new Multiply()).start();
        new Thread(new Div()).start();

        for (int i = 1; i < 1000; i++) {
            for (int j = 1; j < 1000; j++) {
                Msg msg = new Msg(i, j, "((" + i + " + " + j + ") *" + i + ")/2");
                Plus.bq.add(msg);
            }
        }
    }
}
