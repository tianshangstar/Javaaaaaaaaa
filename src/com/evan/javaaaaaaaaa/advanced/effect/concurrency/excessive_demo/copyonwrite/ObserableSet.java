package com.evan.javaaaaaaaaa.advanced.effect.concurrency.excessive_demo.copyonwrite;

import com.evan.javaaaaaaaaa.advanced.effect.concurrency.excessive_demo.ForwardingSet;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by evan01.zhang on 2018/9/18.
 * <p>
 * 用CopyOnWriteArrayList来实现，则不需要通过上锁来实现这个例子
 * 原因：CopyOnWriteArrayList通过戳鞥新拷贝整个底层数组来实现所有写操作
 * 缺点：大量的数据变动不适合，因为进行大量的数组拷贝开销大
 */
public class ObserableSet<E> extends ForwardingSet<E> {
    public ObserableSet(Set<E> s) {
        super(s);
    }

    private final List<SetObserver<E>> observers = new CopyOnWriteArrayList<>();

    /**
     * 订阅通知
     *
     * @param observer 观察者
     */
    public void addObserver(SetObserver<E> observer) {
        observers.add(observer);
    }

    public boolean removeObserver(SetObserver<E> observer) {
        return observers.remove(observer);
    }

    private void notifyElementAdded(E e) {
        for (SetObserver<E> observer : observers) {
            observer.added(this, e);
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
