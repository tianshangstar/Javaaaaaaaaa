package com.evan.javaaaaaaaaa.advanced.thread;

import com.sun.jmx.snmp.tasks.ThreadService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;

/**
 * 如何处理线程中的异常
 */
public class ThreadException {

    static class ExceptionRunnable implements Runnable {
        @Override
        public void run() {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        // 直接try catch
//        defaultTryCatch();
        // 方法1
//        withUncaughtExceptionHandler();
        // 方法2
//        withDefaultExceptionHandler();
        // 方法3 用setDefault
        Thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable e) ->{
            System.out.println("setDefaultUncaughtExceptionHandler");
            e.printStackTrace();
        });
        new Thread(new ExceptionRunnable()).start();
    }

    /**
     * 重写ThreadFactory，为thread添加默认的异常处理器
     */
    private static void withDefaultExceptionHandler() {

        ThreadFactory tf = (Runnable r) -> {
            Thread t = new Thread(r);
            System.out.println("通过factory创建线程");
            t.setUncaughtExceptionHandler((Thread t1, Throwable e) -> {
                        System.out.println("通过ThreadFactory来统一setUncaughtExceptionHandler");
                        e.printStackTrace();
                    }
            );
            return t;
        };

        ExecutorService ts = Executors.newCachedThreadPool(tf);
        for (int i = 0; i < 5; i++) {
            ts.execute(new ExceptionRunnable());
        }
        ts.shutdown();
    }

    private static void withUncaughtExceptionHandler() {
        Thread thread = new Thread(new ExceptionRunnable());
        // 用Thread.setUncaughtExceptionHandler 可以处理线程中的异常
        thread.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
            System.out.println("捕获到了异常");
            e.printStackTrace();
        });

        thread.start();
    }

    private static void defaultTryCatch() {
        //  直接catch并没有办法捕获线程中的异常
        try {

            new Thread(new ExceptionRunnable()).start();
        } catch (Throwable e) {
            System.out.println("捕获到了异常");
            e.printStackTrace();
        }
    }
}
