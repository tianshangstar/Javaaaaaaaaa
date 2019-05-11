package com.evan.javaaaaaaaaa.advanced.effect.concurrency.excessive_demo.copyonwrite;

import java.util.HashSet;

/**
 * Created by evan01.zhang on 2018/9/18.
 * <p>
 */
public class SyncTest1 {
    public static void main(String[] args) {
        ObserableSet<Integer> set = new ObserableSet<>(new HashSet<Integer>());
        set.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObserableSet<Integer> set, Integer element) {
                System.out.println(element);
                if (element == 23)
                    set.removeObserver(this);
            }
        });
        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }
}
