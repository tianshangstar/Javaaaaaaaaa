package com.evan.javaaaaaaaaa.advanced.effect.concurrency.excessive_demo;

import java.util.HashSet;

/**
 * Created by evan01.zhang on 2018/9/18.
 * <p>
 * 演示ConcurrentModificationException
 */
public class SyncTest1 {
    public static void main(String[] args) {
        ObserableSet<Integer> set = new ObserableSet<>(new HashSet<Integer>());
        set.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObserableSet<Integer> set, Integer element) {
                System.out.println(element);
                if (element == 23)
                    // 如果直接这样调用，会提示ConcurrentModificationException
                    // 尝试在同一个线程中，删除一个处在sync状态的set中的数据
                    set.removeObserver(this);
            }
        });
        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }
}
