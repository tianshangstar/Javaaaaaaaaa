package com.evan.javaaaaaaaaa;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by evan01.zhang on 2018/8/20.
 */
public class Test1 {

    static class SelfBounded<T extends SelfBounded<T>> {
        T element;

        SelfBounded<T> set(T arg) {
            element = arg;
            return this;
        }

        T get() {
            return element;
        }
    }

    static class A extends SelfBounded<A> {
    }

    static class B extends SelfBounded<A> {
    }

    static class C extends SelfBounded<C> {
        C setAndGet(C arg) {
            set(arg);
            return get();
        }
    }

    public static void main(String[] args) {
        A a = new A();
        a.set(new A());
        a = a.set(new A()).get();
        a = a.get();
        C c = new C();
        C c1= c.setAndGet(new C());
    }
}
