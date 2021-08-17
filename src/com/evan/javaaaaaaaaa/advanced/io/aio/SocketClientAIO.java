package com.evan.javaaaaaaaaa.advanced.io.aio;

import sun.nio.cs.ext.GBK;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Scanner;
import java.util.concurrent.Future;

public class SocketClientAIO {

    public static void main(String[] args) {
//        futureDemo();

        completionHandlerDemo();
    }

    private static void completionHandlerDemo() {
        try (AsynchronousSocketChannel asynchronousSocketChannel = AsynchronousSocketChannel.open();) {

            asynchronousSocketChannel.connect(new InetSocketAddress("localhost", 8089), asynchronousSocketChannel, new ConnectCompletionHandler());

            int i = 0;

            while (++i < 99999) {
                Thread.sleep(10);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ConnectCompletionHandler
            implements CompletionHandler<Void, AsynchronousSocketChannel> {

        @Override
        public void completed(Void result, AsynchronousSocketChannel attachment) {
            System.err.println(">>>>>>>>>>connect complete<<<<<<<<<<");
            listenInput(attachment);
        }

        @Override
        public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
            exc.printStackTrace();
        }
    }

    private static void listenInput(AsynchronousSocketChannel attachment) {

        Scanner scanner = new Scanner(System.in);

        String inputString;

        int limit = 0;
        while (++limit < 9999) {
            StringBuilder stringBuilder = new StringBuilder();

            while (!(inputString = scanner.next()).equalsIgnoreCase("end")) {
                stringBuilder.append(inputString);
            }

            attachment.write(
                    ByteBuffer.wrap(stringBuilder.toString().getBytes(new GBK())), attachment, new WriteCompletionHandler());
        }
    }

    static class WriteCompletionHandler implements CompletionHandler<Integer, AsynchronousSocketChannel> {

        @Override
        public void completed(Integer result, AsynchronousSocketChannel attachment) {
            System.err.println(">>>>>>>>>>connect complete<<<<<<<<<<");
            System.err.println("sent " + result + " this time!");
        }

        @Override
        public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
            exc.printStackTrace();
        }
    }

    private static void futureDemo() {
        try (AsynchronousSocketChannel asynchronousSocketChannel = AsynchronousSocketChannel.open();) {

            // 这里巨坑。。WindowsAsynchronousSocketChannelImpl 必须要传host，否则会报一个奇怪的错误
            Future<?> connectFuture = asynchronousSocketChannel.connect(new InetSocketAddress("localhost", 8089));

            // 等待连接完成
            while (!connectFuture.isDone()) ;

            Scanner scanner = new Scanner(System.in);

            String inputString;

            int limit = 0;
            while (++limit < 9999) {
                StringBuilder stringBuilder = new StringBuilder();

                while (!(inputString = scanner.next()).equalsIgnoreCase("end")) {
                    stringBuilder.append(inputString);
                }

                Future<Integer> writeFuture = asynchronousSocketChannel.write(
                        ByteBuffer.wrap(stringBuilder.toString().getBytes(new GBK())));
                while (!writeFuture.isDone()) ;//
                System.err.println("发送完了，一共发送了 : " + stringBuilder.length());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
