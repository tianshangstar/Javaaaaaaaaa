package com.evan.javaaaaaaaaa.advanced.io.aio;

import sun.nio.cs.ext.GBK;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SocketServerAIO {

    public static void main(String[] args) throws InterruptedException {
//        futureDemo();

        completionHandlerDemo();
    }

    private static void completionHandlerDemo() {
        try (AsynchronousServerSocketChannel asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();) {

            asynchronousServerSocketChannel.bind(new InetSocketAddress("localhost", 8089));
            AcceptCompletionHandler serverCompletionHandler = new AcceptCompletionHandler(asynchronousServerSocketChannel);
            serverCompletionHandler.init();

            int i = 0;

            while (++i < 99999) {
                Thread.sleep(10);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void futureDemo() {
        try (AsynchronousServerSocketChannel asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();) {

            asynchronousServerSocketChannel.bind(new InetSocketAddress(8089));

            int runLimit = 0;
            while (++runLimit < 9999) {

                System.err.println("runLimit : " + runLimit);

                Future<AsynchronousSocketChannel> future = asynchronousServerSocketChannel.accept();

                while (!future.isDone()) ;// 等待完成

                System.err.println("waiting for input from client");

                AsynchronousSocketChannel socketChannel = future.get(10, TimeUnit.SECONDS);

                ByteBuffer byteBuffer = ByteBuffer.allocate(20);

                Future<Integer> countFuture = socketChannel.read(byteBuffer);

                while (!countFuture.isDone()) ;// 等待完成

                int count = 0;
                while ((count = countFuture.get()) > 0) {
                    byteBuffer.flip();
                    byte[] bytes = Arrays.copyOfRange(byteBuffer.array(), 0, count);
                    System.err.println(new String(bytes));
                    byteBuffer.clear();

                    countFuture = socketChannel.read(byteBuffer);
                    while (!countFuture.isDone()) ;// 等待完成
                }
            }
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    static class AcceptCompletionHandler
            implements CompletionHandler<AsynchronousSocketChannel, Object> {

        private AsynchronousServerSocketChannel asynchronousServerSocketChannel;
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        ReadCompletionHandler readCompletionHandler = new ReadCompletionHandler(byteBuffer,
                asynchronousServerSocketChannel, this);

        AcceptCompletionHandler(AsynchronousServerSocketChannel asynchronousServerSocketChannel) {
            this.asynchronousServerSocketChannel = asynchronousServerSocketChannel;
        }

        void init() {
            asynchronousServerSocketChannel.accept(null, this);
        }

        @Override
        public void completed(AsynchronousSocketChannel result, Object attachment) {
            System.err.println("AcceptCompletionHandler#completed");

            result.read(byteBuffer, result, readCompletionHandler);
        }

        @Override
        public void failed(Throwable exc, Object attachment) {
            exc.printStackTrace();
        }
    }

    static class ReadCompletionHandler
            implements CompletionHandler<Integer, AsynchronousSocketChannel> {

        private ByteBuffer byteBuffer;
        private AsynchronousServerSocketChannel asynchronousServerSocketChannel;

        private AcceptCompletionHandler acceptHandler;

        ReadCompletionHandler(ByteBuffer byteBuffer, AsynchronousServerSocketChannel asynchronousServerSocketChannel,
                              AcceptCompletionHandler acceptHandler) {
            this.byteBuffer = byteBuffer;
            this.asynchronousServerSocketChannel = asynchronousServerSocketChannel;
            this.acceptHandler = acceptHandler;
        }

        @Override
        public void completed(Integer result, AsynchronousSocketChannel attachment) {

//            System.err.println("ReadCompletionHandler#completed");
            if (result > 0) {
                byteBuffer.flip();
                System.err.print(new String(Arrays.copyOfRange(byteBuffer.array(), 0, result), new GBK()));
                byteBuffer.clear();
                attachment.read(byteBuffer, attachment, this);
                if (result < 10) {
                    System.err.println();
                }
            }
//            else {
//                System.err.println(">>>>>>>>>>read all<<<<<<<<<<");
//                asynchronousServerSocketChannel.accept(null, acceptHandler);
//            }
        }

        @Override
        public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
            exc.printStackTrace();
        }
    }
}
