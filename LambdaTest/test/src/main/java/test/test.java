package main.java.test;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class test {
    @Test
    void main() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("Executor是否为守护线程？"+ Thread.currentThread().isDaemon());
                try{
                    TimeUnit.SECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Executor finished");
                return null;
            }
        });

        final CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println("Lambda SupplyAsync without Executor");
            System.out.println("SupplyAsync是否为守护线程" + Thread.currentThread().isDaemon());
            try{
                TimeUnit.SECONDS.sleep(1);

            } catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Completablefuture executed");
            return "result1";

        });

        final CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
            System.out.println("Lambda SupplyAsync with Executor");
            System.out.println("future是否为守护线程" + Thread.currentThread().isDaemon());

            return "result2";
        },executor);
//        System.out.println(future.get());
//        System.out.println(completableFuture.get());
        executor.shutdown();
    }

    @Test
    public static void main(String[] args) {
        System.out.println("2222");
    }





}
