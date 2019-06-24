package com.walle.java8.future;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadExample12 {
    public static void main(String[] args) {
        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(10000);
                return "Hello World";
            }
        });

        new Thread(future, "有返回值的线程").start();

        try {
            System.out.println("子线程的返回值:" + future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
