package com.evan.javaaaaaaaaa.advanced.thread.pool.execotor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by evan01.zhang on 2018/4/26.
 */
public class NewFixed {
    public static void main(String[] args) {
        //具体，见ThreadPoolTest中，LinkedBlockingQueue
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    }
}
