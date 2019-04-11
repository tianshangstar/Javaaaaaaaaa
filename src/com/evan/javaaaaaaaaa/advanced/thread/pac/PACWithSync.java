package com.evan.javaaaaaaaaa.advanced.thread.pac;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PACWithSync {

    private final List<Good> goods = new ArrayList<>();

    class Producer implements Runnable {

        private String name;

        public Producer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            synchronized (goods) {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        goods.add(new Good());
                        System.out.println(String.format("%s --- 创建了一个商品，一共有%s个商品", name, goods.size()));
                        goods.notifyAll();
                        goods.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
            synchronized (goods) {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        if (goods.size() > 0) {
                            goods.remove(goods.size() - 1);
                            System.out.println(String.format("%s --- 消费了一个商品，还有%s个商品", name, goods.size()));
                        }
                        goods.notifyAll();
                        goods.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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

    public static void main(String[] args) {
        PACWithSync pws = new PACWithSync();
        pws.startProducer("生产1");
        pws.startProducer("生产2");
        pws.startConsumer("消费1");
        pws.startConsumer("消费2");
        pws.startConsumer("消费3");
    }
}
