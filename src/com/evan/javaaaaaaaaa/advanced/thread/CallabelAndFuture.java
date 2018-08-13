package com.evan.javaaaaaaaaa.advanced.thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by evan01.zhang on 2018/4/26.
 */
public class CallabelAndFuture {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("callable.call()   ");
                return "Callable.call";
            }
        };

        FutureTask<String> futureTask = new FutureTask<String>(callable);

        // lambda 实现
        FutureTask<String> futureTask1 = new FutureTask<String>(() -> "abc");

        new Thread(futureTask).start();

        Thread.sleep(1500);
        System.out.println(futureTask.get());

        // maxSize 1的单线程池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<Integer> future = threadPool.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        });

        Thread.sleep(1500);
        System.out.println(future.get());

        ExecutorService threadPool1 = Executors.newCachedThreadPool();
        // 按照完成顺序排序的service queue
        CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(threadPool1);
        for (int i = 1; i < 5; i++) {
            final int taskID = i;
            cs.submit(() -> taskID); // lambda.........
        }
        // 可能做一些事情
        for (int i = 1; i < 5; i++) {
            System.out.println(cs.take().get());
        }
    }
}
