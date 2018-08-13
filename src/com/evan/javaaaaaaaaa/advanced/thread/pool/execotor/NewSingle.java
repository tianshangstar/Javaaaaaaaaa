package com.evan.javaaaaaaaaa.advanced.thread.pool.execotor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by evan01.zhang on 2018/4/26.
 */
public class NewSingle {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("first thread");
        });

        executorService.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("second thread");
        });

        executorService.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thrid thread");
        });
    }
}
