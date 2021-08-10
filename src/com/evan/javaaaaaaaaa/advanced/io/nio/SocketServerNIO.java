package com.evan.javaaaaaaaaa.advanced.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SocketServerNIO {

    public static void main(String[] args) throws IOException {

        try (// 1. 创建selector对象
             Selector selector = Selector.open();
             // 2. 创建server socket 通道
             ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {

            // 设置为不阻塞模式
            serverSocketChannel.configureBlocking(false);

            SocketAddress socketAddress = new InetSocketAddress(8089);

            // 绑定端口
            ServerSocket serverSocket = serverSocketChannel.socket();
            serverSocket.bind(socketAddress);

            // 注册，selector开始监听OP_ACCEPT事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.err.println(">>>>>>>>>> server started <<<<<<<<<<");

            int count = 0;
            // 这里之所以这样写，就是因为我不想看sonar lint报错
            while (++count < 1000) {
                // 3. 开始监听事件 这个操作是阻塞的，具体看源码，sync this了
                selector.select();

                // 由于selector.select()操作是阻塞的，所以每次触发的时候，要做到每个selectionKey只处理一次
                Set<SelectionKey> selectKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectKeys.iterator();
                SelectionKey selectionKey;
                while (it.hasNext()) {
                    selectionKey = it.next();
                    // 已经拿出来准备处理的selectionKey移除掉，避免重复处理
                    it.remove();

                    // 处理连接请求
                    if (selectionKey.isAcceptable()) {
                        doAccept(selectionKey, selector);
                    }

                    // 接受到发送过来的数据
                    if (selectionKey.isReadable()) {
                        doRead(selectionKey);
                    }

                }
            }
        }
    }

    private static void doAccept(SelectionKey selectionKey, Selector selector) {
        try {
            // 接受请求
            SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
            // 非阻塞
            socketChannel.configureBlocking(false);
            // selector 开始监听读时间
            socketChannel.register(selector, SelectionKey.OP_READ);
            // 通过socketChannel向客户端发送信息
            socketChannel.write(ByteBuffer.wrap("连接成功".getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println(">>>>>>>>>> find a connection <<<<<<<<<<");
    }

    private static void doRead(SelectionKey selectionKey) {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        // 1k的缓冲区大小（其实好像4k更好诶）
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        System.err.println("---------- do read start ----------");
        // 给客户端写一个回执
        try {
            int readCount = 0;

            while ((readCount = socketChannel.read(byteBuffer)) > 0) {
                // 打印收到的客户端传递信息
                byteBuffer.flip();

                System.err.println(new String(byteBuffer.array()));

                byteBuffer.clear();
            }

            if (readCount == -1) {
                // 客户端已关闭
                System.err.println("客户端是不是断开连接了？");
                socketChannel.close();
            }
            socketChannel.write(ByteBuffer.wrap("你的信息已收到".getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
            if (socketChannel.isOpen()) {
                try {
                    socketChannel.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        System.err.println("---------- do read end ----------");
    }

}
