package com.evan.javaaaaaaaaa.jvm.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一个简单的atomic模型`
 */
public class AtomicTest {

    public static AtomicInteger integer = new AtomicInteger(0);


    public static void increase() {
        integer.incrementAndGet();
    }

    public static final int THREAD_COUNT = 20;

    public static void main(String[] args) {
        System.out.println(integer);
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        System.out.println("thread ---  " + i + "    int = " + integer);
                        increase();
                    }
                }

            };

            threads[i].start();
        }

        // 这里有可能阻塞调，如果想判断所有线程都执行完成，可以考虑使用线程池。
        while (Thread.activeCount() > 1)
            Thread.yield();


        System.out.println(integer);
    }
}
