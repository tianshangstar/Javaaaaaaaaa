package com.evan.javaaaaaaaaa.advanced.thread.pool.threadpoolexecutor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by evan01.zhang on 2018/4/26.
 */
public class ThreadPoolTest {

    private static int corePoolSize = 2;
    private static int maximumPoolSize = 5;
    private static int keepAliveTime = 30;
    private static TimeUnit unit = TimeUnit.SECONDS;
    private static BlockingQueue<Runnable> workQueue;
    private static ThreadFactory threadFactory;
    private static RejectedExecutionHandler handler;

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue = createBlockingQueue(),
                threadFactory = createThreadFactory(),
                handler = createRejectedExecutionHandler()
        );

        for (int i = 0; i < 11; i++) {
            threadPoolExecutor.submit(new PrintRunnable(i));
        }

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(
                        "*************************************" +
                                "\ngetLargestPoolSize = " + threadPoolExecutor.getLargestPoolSize() +
                                "\ngetMaximumPoolSize = " + threadPoolExecutor.getMaximumPoolSize() +
                                "\ngetCorePoolSize = " + threadPoolExecutor.getCorePoolSize() +
                                "\ngetPoolSize = " + threadPoolExecutor.getPoolSize() +
                                "\n*************************************"
                );
            }
        }).start();
    }

    static class PrintRunnable implements Runnable {
        int num = -1;

        public PrintRunnable(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(4000);
                System.out.println(Thread.currentThread().getName() + "run~~~~~~~" + num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static BlockingQueue<Runnable> createBlockingQueue() {
//        return new SynchronousQueue<Runnable>();
//        return new LinkedBlockingQueue<>(4);
//        return new ArrayBlockingQueue<Runnable>(3);
        return new LinkedBlockingDeque<>();
    }

    private static ThreadFactory createThreadFactory() {
        return new DefaultThreadFactory();
    }

    private static RejectedExecutionHandler createRejectedExecutionHandler() {
        return new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("rejectedExecution" + r.getClass().getSimpleName());
            }
        };
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
