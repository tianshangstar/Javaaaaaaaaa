package com.evan.javaaaaaaaaa.advanced.thread.interrupt;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * IO中断
 * -------------------------
 * 手动关闭阻塞的资源（IO）
 * -------------------------
 */
public class TestIOInterrupt {
    private static ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException, InterruptedException {
        testInterrupt(new IOBlocked(System.in), System.in);
        executor.shutdown();
    }

    private static void testInterrupt(Runnable r, InputStream is) throws InterruptedException, IOException {
        Future f = executor.submit(r);
        Thread.sleep(3000);
        is.close();// 可以手动关闭io流，之后中断
//        f.cancel(true);
    }
}
