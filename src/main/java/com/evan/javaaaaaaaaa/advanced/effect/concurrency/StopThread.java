package com.evan.javaaaaaaaaa.advanced.effect.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by evan01.zhang on 2018/9/17.
 */
public class StopThread {

    private static boolean stopFlag;
    //private static volatile boolean stopFlag;// 修订方案1
    //修订方案2，调用下面两个同步方法

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                int i = 0;
                System.out.println(System.currentTimeMillis());
                while (!stopFlag) { // 2、 线程不会停止的原因在这里，虚拟机会将这段代码优化成if (!stopflag) while(true)
                    i++;
                }
                System.out.println(System.currentTimeMillis());
            }
        };
        thread.start();

        TimeUnit.SECONDS.sleep(1);

        stopFlag = true; // 1、这个线程将永远不会停止。
//        stopThread();
    }

    private static synchronized void stopThread() {
        stopFlag = true;
    }

    private static synchronized boolean isStop() {
        return stopFlag;
    }
}
