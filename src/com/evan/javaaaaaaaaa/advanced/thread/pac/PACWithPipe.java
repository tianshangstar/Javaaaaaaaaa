package com.evan.javaaaaaaaaa.advanced.thread.pac;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class PACWithPipe {


    private AtomicInteger productCounter = new AtomicInteger(0);
    private AtomicInteger consumeCounter = new AtomicInteger(0);

    private final PipedOutputStream pipeOut;
    private final PipedInputStream pipeIn;

    public PACWithPipe() throws IOException {
        this.pipeOut = new PipedOutputStream();
        this.pipeIn = new PipedInputStream(pipeOut);
    }

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
                    productCounter.set(productCounter.get() + 1);

                    Good g = new Good();
                    new ObjectOutputStream(pipeOut).writeObject(g);
                    pipeOut.flush();
                    System.out.println(String.format("%s --- 创建了一个商品，一共生产了%s个", name, productCounter.get()));
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        pipeOut.close();
                    } catch (IOException e) {
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
            while (true) {
                try {
//                    Thread.sleep(1000);
                    System.out.println(new ObjectInputStream(pipeIn).readObject());
                    System.out.println(String.format("%s --- 消费了一个商品，一共消费了%s个", name, consumeCounter.get()));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        pipeIn.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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

    public static void main(String[] args) throws IOException {
        PACWithPipe pwp = new PACWithPipe();
        // 管道操作要端对端。。。 否则会报错哦。
        pwp.startProducer("生产1");
//        pwp.startProducer("生产2");
        pwp.startConsumer("消费1");
//        pwp.startConsumer("消费2");
//        pwp.startConsumer("消费3");
    }
}
