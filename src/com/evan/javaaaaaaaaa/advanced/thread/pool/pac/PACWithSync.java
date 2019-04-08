package com.evan.javaaaaaaaaa.advanced.thread.pool.pac;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PACWithSync {

    private final List<Good> goods = new ArrayList<>();

    class Producer implements Runnable {
        @Override
        public void run() {
            synchronized (goods) {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        goods.add(new Good());
                        System.out.println(String.format("创建了一个商品，一共有%s个商品", goods.size()));
                        goods.notify();
                        goods.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    class Consumer implements Runnable {

        @Override
        public void run() {
            synchronized (goods) {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        if (goods.size() > 0) {
                            goods.remove(goods.size() - 1);
                            System.out.println(String.format("消费了一个商品，还有%s个商品", goods.size()));
                        }
                        goods.notify();
                        goods.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
        PACWithSync pws = new PACWithSync();
        pws.startProducer();
        pws.startConsumer();
    }
}
