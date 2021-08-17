package com.evan.javaaaaaaaaa.advanced.io.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class FileAIO {

    public static void main(String[] args) {
//        futureDemo();

        completionHandlerDemo();
    }

    private static void completionHandlerDemo() {
        try (
                // 创建异步通道
                AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(
                        Paths.get("D:\\develop\\workspace\\other\\Javaaaaaaaaa\\src\\com\\evan\\javaaaaaaaaa\\advanced\\io\\file_for_test"),
                        StandardOpenOption.READ, StandardOpenOption.WRITE
                )) {

            CompletionHandlerImpl completionHandler = new CompletionHandlerImpl(asynchronousFileChannel);
            completionHandler.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void futureDemo() {
        try (
                // 创建异步通道
                AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(
                        Paths.get("D:\\develop\\workspace\\other\\Javaaaaaaaaa\\src\\com\\evan\\javaaaaaaaaa\\advanced\\io\\file_for_test"),
                        StandardOpenOption.READ, StandardOpenOption.WRITE
                )) {

            ByteBuffer byteBuffer = ByteBuffer.allocate(20);

            long size = asynchronousFileChannel.size();
            // future模式
            Future<Integer> future;

            int position = 0;
            while (size > position) {
                future = asynchronousFileChannel.read(byteBuffer, position);
                while (!future.isDone()) ; // 等待完成
                int current = future.get();
                position += current;
                byteBuffer.flip();

                byte[] bytes;
                bytes = Arrays.copyOfRange(byteBuffer.array(), 0, current);

                System.err.print(new String(bytes));
                byteBuffer.clear();
            }
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class CompletionHandlerImpl implements CompletionHandler<Integer, Object> {

        long position = 0;
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        AsynchronousFileChannel asynchronousFileChannel;
        long size = 0;

        CompletionHandlerImpl(AsynchronousFileChannel asynchronousFileChannel) throws IOException {
            this.asynchronousFileChannel = asynchronousFileChannel;
            size = asynchronousFileChannel.size();
        }

        void read() {
            asynchronousFileChannel.read(byteBuffer, position, null, this);
        }

        @Override
        public void completed(Integer result, Object attachment) {

            position = position + result;

            byteBuffer.flip();
            byte[] bytes = Arrays.copyOfRange(byteBuffer.array(), 0, result);
            System.err.print(new String(bytes));

            byteBuffer.clear();
            if (size > position)
                asynchronousFileChannel.read(byteBuffer, position, null, this);
        }

        @Override
        public void failed(Throwable exc, Object attachment) {
            exc.printStackTrace();
        }
    }

}
