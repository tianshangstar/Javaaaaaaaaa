package com.evan.javaaaaaaaaa.advanced.io.nio;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class SocketClientNIO {


    public static void main(String[] args) {
        Client client = new Client("client-1");
//        Client client1 = new Client("client-2");
        client.init();
    }

    static class Client {

        String clientName;

        Client(String clientName) {
            this.clientName = clientName;
            init();
        }

        /**
         * client需要处理的事件:
         *
         * @see SelectionKey#OP_CONNECT
         * @see SelectionKey#OP_READ
         * @see SelectionKey#OP_WRITE
         */
        private void init() {
            try (// 1. 初始化selector
                 Selector selector = Selector.open();
                 // 2. 初始化channel
                 SocketChannel socketChannel = SocketChannel.open()) {

                socketChannel.configureBlocking(false);

                // 3. selector注册channel
                SocketAddress socketAddress = new InetSocketAddress(8089);
                socketChannel.register(selector, SelectionKey.OP_CONNECT);

                socketChannel.connect(socketAddress);

                // 开始监听
                int i = 0;
                while (++i < 9999) {
                    System.err.println(i);
                    selector.select();

                    Iterator<SelectionKey> it = selector.selectedKeys().iterator();

                    while (it.hasNext()) {
                        SelectionKey selectionKey = it.next();
                        it.remove();
                        if (selectionKey.isConnectable()) {
                            while (!((SocketChannel) selectionKey.channel()).finishConnect()) {
                                // 等待连接
                            }
                            doConnect(selectionKey, selector);
                        } else if (selectionKey.isReadable()) {
                            doRead(selectionKey);
                        } else if (selectionKey.isWritable()) {
                            doWrite(selectionKey);
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void doConnect(SelectionKey selectionKey, Selector selector) throws ClosedChannelException {
            System.err.println("---------- do connect start ----------");
            selectionKey.channel().register(selector, SelectionKey.OP_READ);
            selectionKey.channel().register(selector, SelectionKey.OP_WRITE);
            System.err.println("---------- do connect end ----------");
        }

        private void doRead(SelectionKey selectionKey) throws IOException {
            System.err.println("---------- do read start ----------");
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (socketChannel.read(byteBuffer) > 0) {
                byteBuffer.flip();
                System.err.println("<" + clientName + "> receive message : " + new String(byteBuffer.array()));
                byteBuffer.clear();
            }
            System.err.println("---------- do read end ----------");
        }

        private void doWrite(SelectionKey selectionKey) throws IOException {
            System.err.println("---------- do write start ----------");
            Scanner scanner = new Scanner(System.in);
            System.err.println("<" + clientName + "> : input sth");

            StringBuilder stringBuilder = new StringBuilder();
            String currentInput = null;
            while (!StringUtils.equalsIgnoreCase(currentInput = scanner.next(), "end")) {
                stringBuilder.append(currentInput);
            }

            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            socketChannel.write(ByteBuffer.wrap(stringBuilder.toString().getBytes()));

//            scanner.close();
            System.err.println("---------- do write end ----------");
        }
    }

}
