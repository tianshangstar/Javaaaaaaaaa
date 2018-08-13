package com.evan.javaaaaaaaaa.advanced.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evan01.zhang on 2018/4/26.
 */
public class ThreadSyncDemo {

    private static List<Goods> goodsList = new ArrayList();


    public static void main(String[] args) {
        new Thread(new Producter("producter A ")).start();
//        new Thread(new Producter("producter B ")).start();
//        new Thread(new Producter("producter C ")).start();

        new Consumer("consumer A ").start();
//        new Consumer("consumer B ").start();
//        new Consumer("consumer C ").start();
    }


    static class Producter implements Runnable {

        String producterName = "unKnown";

        public Producter(String producterName) {
            this.producterName = producterName;
        }

        @Override
        public void run() {
            synchronized (goodsList) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                goodsList.add(new Goods());
                System.out.println(producterName + " make a good, there is " + goodsList.size() + " good");
                try {
//                    if (goodsList.size() >= 10) {
                    goodsList.wait();
                    goodsList.notifyAll();
//                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer extends Thread {


        String consumerName = "unKnown";

        public Consumer(String consumerName) {
            this.consumerName = consumerName;
        }

        @Override
        public void run() {
            synchronized (goodsList) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (goodsList.size() > 0) {
                    goodsList.remove(0);
                    System.out.println(consumerName + " consume a good");
                } else {
                    try {
                        goodsList.wait();
                        goodsList.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Goods {
    }

}
