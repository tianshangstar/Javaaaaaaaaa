package com.evan.javaaaaaaaaa.advanced.thread.interrupt;

import java.io.IOException;
import java.io.InputStream;

/**
 * 线程阻塞模型：io
 */
public class IOBlocked implements Runnable {

    private InputStream is;

    public IOBlocked(InputStream is) {
        this.is = is;
    }

    @Override
    public void run() {
        try {
            System.out.println("IOBlocked read start-----------");
            // 通过io来阻塞线程
            is.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("IOBlocked exit ------------");
    }
}
