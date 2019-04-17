package com.evan.javaaaaaaaaa.advanced.thread.pac;

import java.util.List;
import java.util.concurrent.*;

public class PACWithExchanger {

    static class Producer implements Runnable {

        private Exchanger<List<Good>> pExchanger;

        private List<Good> pList;

        public Producer(Exchanger ex, List<Good> pList) {
            this.pExchanger = ex;
            this.pList = pList;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    if (pList.size() > 4) {
                        // 交互目标对象，主要要重新赋值
                        pList = pExchanger.exchange(pList);
                    } else {
                        System.out.println(String.format("生产一个，一共有%s个", pList.size()));
                        pList.add(new Good());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {

        private Exchanger<List<Good>> cExchanger;

        private List<Good> cList;

        public Consumer(Exchanger ex, List<Good> cList) {
            this.cExchanger = ex;
            this.cList = cList;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    if (cList.size() > 0) {
                        System.out.println(String.format("消费一个，还有%s个", cList.size()));
                        cList.remove(cList.size() - 1);
                    } else {
                        System.out.println("消费完了");
                        // 交互目标对象，主要要重新赋值
                        cList = cExchanger.exchange(cList);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // exchanger
        Exchanger<List<Good>> ex = new Exchanger<>();
        // concurrent包新增，遍历的同事remove不报错的list
        List<Good> list1 = new CopyOnWriteArrayList<>();
        List<Good> list2 = new CopyOnWriteArrayList<>();

        ExecutorService es = Executors.newFixedThreadPool(2);
        es.execute(new Producer(ex, list1));
        es.execute(new Consumer(ex, list2));

        TimeUnit.SECONDS.sleep(5);
        es.shutdown();
    }
}
