package com.evan.javaaaaaaaaa.advanced.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class SocketClientBIO {

    public static void main(String[] args) {

        SocketAddress socketAddress = new InetSocketAddress("localhost", 8088);

        try {

            Socket socket = new Socket();
            Scanner scanner = new Scanner(System.in);

            String input = null;

            while ((input = scanner.next()) != "end") {
                socket = new Socket("localhost", 8088);
                OutputStream outputStream = socket.getOutputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                outputStream.write(input.getBytes());
                outputStream.flush();
                socket.shutdownOutput();
                String response;
                while ((response = bufferedReader.readLine()) != null) {
                    System.err.println(response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
