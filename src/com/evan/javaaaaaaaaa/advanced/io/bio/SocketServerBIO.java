package com.evan.javaaaaaaaaa.advanced.io.bio;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 这个例子，实现了一个监听本机8088端口的简单服务端
 * 但是是基于bio的
 */
public class SocketServerBIO {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(8088)) {
            int i = 0;
            // 这么写就是不想看sonar的告警，艹
            while (++i < 9999) {
                Socket socket = serverSocket.accept();
                executorService.execute(new ServerThread(socket, "This is consumer<" + i + ">"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ServerThread implements Runnable {

        String threadName;
        Socket socket;

        ServerThread(Socket socket, String threadName) {
            this.socket = socket;
            this.threadName = threadName;
        }

        @Override
        public void run() {

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                String info;
                while (StringUtils.isNotEmpty(info = reader.readLine())) {
                    System.err.println("<" + threadName + ">" + "received msg : " + info);
                }

                socket.shutdownInput();
                writer.newLine();
                writer.write("<" + threadName + ">" + "read finished! bye!");
                writer.flush();
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
