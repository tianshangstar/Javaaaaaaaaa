package com.evan.javaaaaaaaaa.advanced.effect.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by evan01.zhang on 2018/9/19.
 * <p>
 * 很有意思的一个小工具
 * <p>
 * CountDownLatch: 计数
 * await：等待计数完成，并阻塞当前线程
 * countDown：计数器减一
 */
public class CountDownLatchDemo {
    static volatile Integer count = 0;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(time(Executors.newCachedThreadPool(), 10, () -> {
            synchronized (count) {
                System.out.println("this is action : count = " + (count++));
            }
        }));
    }

    public static long time(Executor executor, int concurrency, final Runnable action) throws InterruptedException {
        final CountDownLatch ready = new CountDownLatch(concurrency);
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch done = new CountDownLatch(concurrency);
        for (int i = 0; i < concurrency; i++) {
            executor.execute(() -> {
                ready.countDown();
//                System.out.println(ready.getCount());
                try {
                    start.await();
                    action.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                } finally {
                    done.countDown();
                }
            });
        }
        ready.await();
        long startNanos = System.nanoTime();
        start.countDown();
        done.await();
        return System.nanoTime() - startNanos;
    }
}
