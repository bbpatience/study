package com.walle.concurrent.future.jdkfuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author: bbpatience
 * @date: 2018/12/20
 * @description: FutureMain
 **/
public class FutureMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> future = new FutureTask<>(new RealData("a"));

        ExecutorService es = Executors.newFixedThreadPool(1);
        es.submit(future);

        System.out.println("request done.");

        Thread.sleep(2000);

        System.out.println("data = " + future.get());
    }
}
