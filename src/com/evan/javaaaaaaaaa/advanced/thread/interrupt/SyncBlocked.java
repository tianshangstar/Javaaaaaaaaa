package com.evan.javaaaaaaaaa.advanced.thread.interrupt;

/**
 * 线程阻塞模型：死锁
 */
public class SyncBlocked implements Runnable {

    public SyncBlocked() {
        // 构造函数，启动一个线程无限调用doSth
        // SyncBlocked的run也去调doSth，模拟死锁
        new Thread(this::doSth).start();
    }

    private synchronized void doSth() {
        while (true)
            Thread.yield();
    }

    @Override
    public void run() {
        System.out.println("SyncBlocked start---------");
        doSth();
        System.out.println("SyncBlocked exit---------");
    }
}
