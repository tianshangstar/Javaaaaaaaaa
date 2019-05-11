package com.evan.javaaaaaaaaa.advanced.thread;

/**
 * Created by evan01.zhang on 2018/4/26.
 */
public class MyThread {

    static Object lock = new Object();

    public static void main(String[] args) {

        Thread threadA = new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("thread a run");
            }
        };

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("thread b run");
            }
        });

        threadA.start();
        threadB.start();

    }
}
