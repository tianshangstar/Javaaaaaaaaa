package com.evan.javaaaaaaaaa.advanced.thread.pool.pac;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PACWIthLock {
    private final List<Good> goods = new ArrayList<>();
    private Lock lock = new ReentrantLock(true);

    class Producer implements Runnable {
        @Override
        public void run() {
            boolean isLock = false;
            while (true) {
                try {
                    isLock = lock.tryLock(2, TimeUnit.SECONDS);
                    Thread.sleep(1000);
                    goods.add(new Good());
                    System.out.println(String.format("创建了一个商品，一共有%s个商品", goods.size()));
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

        @Override
        public void run() {
            boolean isLock = false;
            while (true) {
                try {
                    isLock = lock.tryLock(2, TimeUnit.SECONDS);
                    Thread.sleep(1000);
                    if (goods.size() > 0) {
                        goods.remove(goods.size() - 1);
                        System.out.println(String.format("消费了一个商品，还有%s个商品", goods.size()));
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (isLock)
                        lock.unlock();
                }
            }
        }
    }

    private void startProducer() {
        executor.execute(new Thread(new Producer()));
    }

    private void startConsumer() {
        executor.execute(new Thread(new Consumer()));
    }

    private ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        PACWIthLock pws = new PACWIthLock();
        pws.startProducer();
        pws.startConsumer();
    }
}
