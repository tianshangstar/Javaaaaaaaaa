package com.evan.javaaaaaaaaa.advanced.io.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;
import java.util.Arrays;

/**
 * nio是基于selector、channel、buffer的
 * 反正我就觉得java的io难用，及其难用
 * -------------
 * 这个例子只演示了一部分
 * 如果需要注册到selector，需要实现SelectableChannel，但是fileChannel没有
 */
public class FileNIO {


    public static void main(String[] args) throws IOException {

        File file = new File(
                "D:\\develop\\workspace\\other\\Javaaaaaaaaa\\src\\com\\evan\\javaaaaaaaaa\\advanced\\io\\file_for_test");


        // 创建fileChannel
        FileChannel fileChannel = new RandomAccessFile(file, "rw").getChannel();

        // 创建算择期
        Selector selector = Selector.open();

        // 对应的fileChannel注册到selector
//        SelectionKey selectionKey = fileChannel.

        // 缓冲
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        int currentLength = 0;

        StringBuilder stringBuilder = new StringBuilder();

        fileChannel.write(byteBuffer);

        while ((currentLength = fileChannel.read(byteBuffer)) != -1) {
            byteBuffer.flip();
            byte[] bytes;
            bytes = Arrays.copyOfRange(byteBuffer.array(), 0, currentLength);

            stringBuilder.append(new String(bytes));

            System.err.println(new String(bytes));
            byteBuffer.clear();
        }


        System.err.println(stringBuilder.toString());
    }

}
