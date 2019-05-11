package com.evan.javaaaaaaaaa.advanced.thread.pool.execotor;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by evan01.zhang on 2018/4/26.
 */
public class NewSchedule {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2, new DefaultThreadFactory());

        // 线程延迟5秒执行
        executorService.schedule(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("first thread");
        }, 5, TimeUnit.SECONDS);

        // 线程延迟5秒启动，每5秒执行一次
        // 第一个start以后，直接开始delay下个start
        executorService.scheduleAtFixedRate(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("second thread" + new Date());
        }, 5, 5, TimeUnit.SECONDS);

        // 前一个执行完，再delay对应的时间start 下一个线程
        executorService.scheduleWithFixedDelay(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thrid thread" + new Date());
        }, 5, 5 , TimeUnit.SECONDS);
    }

    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager var1 = System.getSecurityManager();
            this.group = var1 != null ? var1.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable var1) {
            Thread var2 = new Thread(this.group, var1, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
            if (var2.isDaemon()) {
                var2.setDaemon(false);
            }

            if (var2.getPriority() != 5) {
                var2.setPriority(5);
            }

            return var2;
        }
    }
}
