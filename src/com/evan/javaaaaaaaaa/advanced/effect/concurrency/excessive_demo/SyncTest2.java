package com.evan.javaaaaaaaaa.advanced.effect.concurrency.excessive_demo;

import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by evan01.zhang on 2018/9/18.
 * <p>
 * 演示死锁
 * 原因：
 * 调用set.removeOberver时，尝试 synchronized (observers),但是无法成功，因为主线程已经拥有了对象锁
 * 在这时，主线程也在等待后台线程来完成对观察者的删除
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
