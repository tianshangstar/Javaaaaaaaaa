package com.evan.javaaaaaaaaa.advanced.effect.singleton;

import java.io.Serializable;

public class ReadResolveSingleton implements Serializable {

    private static volatile ReadResolveSingleton instance = null;

    public static ReadResolveSingleton getInstance() {
        if (instance == null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new ReadResolveSingleton();
                }
            }
        }

        return instance;
    }

    private ReadResolveSingleton() {
        if (instance != null)
            throw new IllegalStateException("multi obj in singleton");
    }

    private Object readResolve(){
        return getInstance();
    }
}
