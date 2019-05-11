package com.evan.javaaaaaaaaa.jvm.jvm_exception;

import java.util.ArrayList;
import java.util.List;

/**
 * java堆异常：java.lang.OutOfMemoryError: Java heap space
 * <p>
 * jvm options : -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }

    }
}
