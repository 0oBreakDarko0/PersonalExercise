package org.myself.study.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author mapengfei
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //无返回值
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ": hello");
        });

        //有返回值
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "hello");
        String returnValue = future2.get();
        System.out.println("有返回值的CompletableFuture:" + returnValue);

        //接收上一个的返回值作为入参，继续进行处理
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World! ");
        System.out.println(future3.get());


        CompletableFuture<String> future4 = CompletableFuture.completedFuture("Hello ")
                .thenApply(s -> s + "World ")
                .thenApply(s -> s + " Nice !");
        System.out.println(future4.get());

        //不需要从回调函数中获取返回结果：thenAccept 能够访问异步计算的结果
        CompletableFuture.completedFuture("Hello ")
                .thenApply(s -> s + "World ")
                .thenApply(s -> s + "Nice !")
                .thenAccept(System.out::println);

        CompletableFuture.completedFuture("Hello ")
                .thenApply(s -> s + "World ")
                .thenApply(s -> s + "Nice !")
                .thenRun(() -> System.out.println("Hello thenRun"));

        CompletableFuture<String> future5 = CompletableFuture.supplyAsync(() -> "hello")
                .whenComplete((res, ex) -> {
                    System.out.println("WhenComplete:" + res);
                });
        System.out.println(future5.get());

        //异常处理：handle
        CompletableFuture<String> future6 = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("测试Handle方法异常处理");
            }

            return "Hello";
        }).handle((res, ex) -> {
            return res != null ? res : "World";
        });
        System.out.println(future6.get());

        //异常处理：exceptionally
        CompletableFuture<String> future7 = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("测试 exceptionally() 异常方法处理");
            }

            return "Hello";
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return "World";
        });
        System.out.println(future7.get());

        //组合CompletableFuture
        //1. thenCompose: 组合多个CompletableFuture，前面的可以作为后面的参数
        CompletableFuture<Object> future8 = CompletableFuture.supplyAsync(() -> "Hello ")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "thenCompose World"));
        System.out.println(future8.get());

        //2. thenCombine: 并行执行，无依赖关系
        CompletableFuture<String> future9 = CompletableFuture.supplyAsync(() -> "Hello ")
                .thenCombine(CompletableFuture.supplyAsync(() -> "thenCombine"), (s1, s2) -> s1 + s2);
        System.out.println(future9.get());

        List<CompletableFuture> futureList = new ArrayList<>();
        for(int i = 0; i < 10; ++i) {
            CompletableFuture<Void> tempFuture = CompletableFuture.runAsync(() -> System.out.println("Hello"));
            futureList.add(tempFuture);
        }
        CompletableFuture<Void> future10 = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));

        try {
            future10.join();
        } catch (Exception e) {

        }

        System.out.println("All Done!!!");
    }
}
