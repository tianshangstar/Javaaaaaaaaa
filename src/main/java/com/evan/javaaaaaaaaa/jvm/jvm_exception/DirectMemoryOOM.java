package com.evan.javaaaaaaaaa.jvm.jvm_exception;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * VM Options:-Xmx20m -XX:MaxDirectMemorySize=10m
 * <p>
 * Created by evan01.zhang on 2018/3/22.
 */
public class DirectMemoryOOM {
    private static final int _1mb = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];

        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        while (true) {
            unsafe.allocateMemory(_1mb);
        }
    }
}
