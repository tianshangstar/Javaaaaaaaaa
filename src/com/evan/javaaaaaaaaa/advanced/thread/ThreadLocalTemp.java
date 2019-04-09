package com.evan.javaaaaaaaaa.advanced.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 具体ThreadLocal如何工作的
 * 需要看的源码主要是两部分
 * 1、Thread.currentThread()
 * 2、Thread.ThreadLocalMap()
 */
public class ThreadLocalTemp {
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> {
        System.out.println("调用了初始化方法");
        return 99988789;
    });

    static class Accessor implements Runnable {

        String threadName;

        public Accessor(int id) {
            threadLocal.set(id);
            threadName = "thread " + id;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    int offset = (int) (Math.random() * 10);
                    threadLocal.set(threadLocal.get() + offset);
                    System.out.println(this + " offset = " + offset);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        @Override
        public String toString() {
            return "threadName " + threadName + " ---- " + "id = " + threadLocal.get();
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool(new MyThreadFactory());
        for (int i = 0; i < 5; i++) {
            es.execute(new Accessor(i));
        }
        es.shutdown();
    }

    static class MyThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setUncaughtExceptionHandler((Thread th, Throwable throwable) -> {
                System.out.println(r.toString());
                throwable.printStackTrace();
            });

            return t;
        }
    }
}
