package com.evan.javaaaaaaaaa.advanced.effect.concurrency.excessive_demo;

/**
 * Created by evan01.zhang on 2018/9/18.
 */
public interface SetObserver<E> {
    void added(ObserableSet<E> set, E element);
}
