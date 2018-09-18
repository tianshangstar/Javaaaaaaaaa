package com.evan.javaaaaaaaaa.advanced.effect.concurrency.excessive_demo.copyonwrite;

import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by evan01.zhang on 2018/9/18.
 * <p>
 */
public class SyncTest2 {
    public static void main(String[] args) {
        ObserableSet<Integer> set = new ObserableSet<>(new HashSet<Integer>());
        set.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObserableSet<Integer> set, Integer element) {
                System.out.println(element);
                if (element == 23) {
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    final SetObserver<Integer> observer = this;

                    try {
                        executorService.submit(() -> {
                            set.removeObserver(observer);
                        }).get();
                    } catch (Exception e) {
                        // do nothing
                    } finally {
                        executorService.shutdown();
                    }
                }
            }
        });
        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }
}
