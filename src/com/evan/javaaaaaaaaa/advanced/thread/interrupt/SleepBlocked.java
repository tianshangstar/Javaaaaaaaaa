package com.evan.javaaaaaaaaa.advanced.thread.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * 线程阻塞模型：sleep
 */
public class SleepBlocked implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("sleepBlocked start---------");
            // sleep 100s模拟线程阻塞
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("sleepBlocked interrupted");
        }
        System.out.println("sleepBlocked exit---------");
    }
}
