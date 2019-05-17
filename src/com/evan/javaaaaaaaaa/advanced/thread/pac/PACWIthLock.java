package com.evan.javaaaaaaaaa.advanced.thread.pac;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PACWIthLock {
    private volatile List<Good> goods = new ArrayList<>();
    private static Lock lock = new ReentrantLock(true);
    private static Condition condition = lock.newCondition();
    private AtomicInteger productCounter = new AtomicInteger(0);
    private AtomicInteger consumeCounter = new AtomicInteger(0);

    class Producer implements Runnable {
        private String name;

        public Producer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            boolean isLock = false;
            while (true) {
                try {
                    Thread.sleep(1000);
                    isLock = lock.tryLock(2, TimeUnit.SECONDS);
                    if (goods.size() > 2) {
                        if (isLock)
                            condition.await();
                    }
                    goods.add(new Good());
                    productCounter.set(productCounter.get() + 1);
                    System.out.println(String.format("%s --- 创建了一个商品，一共有%s个商品，一共生产了%s个", name, goods.size(), productCounter.get()));
                    if (isLock)
                        condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (isLock)
                        lock.unlock();
                }
            }
        }
    }


    class Consumer implements Runnable {
        private String name;

        public Consumer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            boolean isLock = false;
            while (true) {
                try {
                    Thread.sleep(1000);
                    isLock = lock.tryLock(2, TimeUnit.SECONDS);

                    if (goods.size() > 0) {
                        goods.remove(goods.size() - 1);
                        consumeCounter.set(consumeCounter.get() + 1);
                        System.out.println(String.format("%s --- 消费了一个商品，还有%s个商品，一共消费了%s个", name, goods.size(), consumeCounter.get()));
                    } else {
                        if (isLock)
                            condition.await();
                    }
                    if (isLock)
                        condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (isLock)
                        lock.unlock();
                }
            }
        }
    }

    private void startProducer(String name) {
        executor.execute(new Thread(new Producer(name)));
    }

    private void startConsumer(String name) {
        executor.execute(new Thread(new Consumer(name)));
    }

    private ExecutorService executor = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws InterruptedException {
        PACWIthLock pws = new PACWIthLock();
        pws.startProducer("生产1");
        pws.startProducer("生产2");
        pws.startConsumer("消费1");
        pws.startConsumer("消费2");
        pws.startConsumer("消费3");

        Thread.sleep(10000);
        System.out.println(pws.productCounter.get());
        System.out.println(pws.consumeCounter.get());
    }
}
