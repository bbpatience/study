package com.walle.java8.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author: bbpatience
 * @date: 2018/12/21
 * @description: CalculateDemo
 **/
public class CalculateDemo {
    public static Integer calc(Integer para) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        return para * para;
    }

    public static Integer div(Integer para) {
        return para / 2;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> fu = CompletableFuture.supplyAsync(() -> div(50))
            .thenCompose((i) -> CompletableFuture.supplyAsync(() -> div(i)))
            .exceptionally(ex -> {
                System.out.println(ex.toString());
                return 0;
            })
            .thenApply(i -> Integer.toString(i))
            .thenApply(str -> "\"" + str + "\"")
            .thenAccept(System.out::println);
        fu.get();

        CompletableFuture<Integer> intFuture = CompletableFuture.supplyAsync(() -> div(50));
        CompletableFuture<Integer> intFuture2 = CompletableFuture.supplyAsync(() -> div(25));

        CompletableFuture<Void> fu2 = intFuture.thenCombine(intFuture2, (i,j) -> (i + j))
            .thenApply(str -> "\"" + str + "\"")
            .thenAccept(System.out::println);
        fu2.get();
    }
}
