package com.evan.javaaaaaaaaa.advanced.thread.interrupt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestInterrupt {
    private static ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {

        testInterrupt(new IOBlocked(System.in)); // 无法中断
        testInterrupt(new SyncBlocked()); // 无法中断
        testInterrupt(new SleepBlocked()); // 可以中断
        executor.shutdown();
    }

    private static void testInterrupt(Runnable r) throws InterruptedException {
        System.out.println("testInterrupt -> " + r.getClass().getSimpleName());
        Future f = executor.submit(r);
        // 这个sleep别删，否则直接cancel，无法执行。
        Thread.sleep(300);
        f.cancel(true);
        new Thread().interrupt();
    }
}
