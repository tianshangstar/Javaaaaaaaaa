package com.evan.javaaaaaaaaa.advanced.thread.pac;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PACWithQueue {

    //    private BlockingQueue<Good> queue = new ArrayBlockingQueue<>(10);// ArrayBlockingQueue需要指定初始化数组大小
    private BlockingQueue<Good> queue = new LinkedBlockingQueue<>();// LinkedBlockingQueue 无界的
    private AtomicInteger productCounter = new AtomicInteger(0);
    private AtomicInteger consumeCounter = new AtomicInteger(0);

    class Producer implements Runnable {
        private String name;

        public Producer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    queue.put(new Good());
                    productCounter.set(productCounter.get() + 1);
                    System.out.println(String.format("%s --- 创建了一个商品，一共有%s个商品，一共生产了%s个", name, queue.size(), productCounter.get()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
            while (true) {
                try {
                    Thread.sleep(1000);
                    if (queue.size() > 0) {
                        queue.poll();
                        consumeCounter.set(consumeCounter.get() + 1);
                        System.out.println(String.format("%s --- 消费了一个商品，还有%s个商品，一共消费了%s个", name, queue.size(), consumeCounter.get()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private ExecutorService executor = Executors.newFixedThreadPool(5);

    private void startProducer(String name) {
        executor.execute(new Thread(new Producer(name)));
    }

    private void startConsumer(String name) {
        executor.execute(new Thread(new Consumer(name)));
    }

    public static void main(String[] args) {
        PACWithQueue pwq = new PACWithQueue();
        pwq.startProducer("生产1");
        pwq.startProducer("生产2");
//        pwq.startConsumer("消费1");
//        pwq.startConsumer("消费2");
        pwq.startConsumer("消费3");
    }

}
