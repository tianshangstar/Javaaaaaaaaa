package com.evan.javaaaaaaaaa.advanced.effect.concurrency.excessive_demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by evan01.zhang on 2018/9/18.
 * <p>
 * 实现了一个观察者模式，用来模拟同步问题，会在test中破坏这个观察者模式
 */
public class ObserableSet<E> extends ForwardingSet<E> {
    public ObserableSet(Set<E> s) {
        super(s);
    }

    private final List<SetObserver<E>> observers = new ArrayList<>();

    /**
     * 订阅通知
     *
     * @param observer 观察者
     */
    public void addObserver(SetObserver<E> observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public boolean removeObserver(SetObserver<E> observer) {
        synchronized (observers) {
            return observers.remove(observer);
        }
    }

    private void notifyElementAdded(E e) {
        synchronized (observers) {
            for (SetObserver<E> observer : observers) {
                observer.added(this, e);
            }
        }
    }

    @Override
    public boolean add(E e) {
        boolean added = super.add(e);
        if (added)
            notifyElementAdded(e);
        return added;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = super.addAll(c);
        for (E e : c) {
            result |= add(e);
        }
        return result;
    }
}
