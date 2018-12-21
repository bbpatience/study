package com.walle.java8.future;

import java.util.concurrent.CompletableFuture;

/**
 * @author: bbpatience
 * @date: 2018/12/21
 * @description: AskThread
 **/
public class AskThread implements Runnable {

    CompletableFuture<Integer> re = null;
    public AskThread(CompletableFuture<Integer> re) {
        this.re = re;
    }

    @Override
    public void run() {
        int myRe = 0;
        try {
            myRe = re.get() * re.get();
        } catch (Exception e) {

        }
        System.out.println("myRe:" + myRe);
    }

    public static void main(String[] args) throws InterruptedException {
        final CompletableFuture<Integer> future = new CompletableFuture<>();
        new Thread(new AskThread(future)).start();
        Thread.sleep(1000);
        future.complete(60);
    }
}
