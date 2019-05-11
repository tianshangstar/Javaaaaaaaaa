package com.evan.javaaaaaaaaa.advanced.rmi;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

public class MyRmiClientFactory extends RMISocketFactory implements Serializable {


    private static final long serialVersionUID = -3584454046925266841L;
    private final String host;
    private final int port;

    public MyRmiClientFactory(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        System.out.println("create socket : host is : " + host + "    port is : " + port);
        return new Socket(this.host, this.port);
    }

    @Override
    public ServerSocket createServerSocket(int port) throws IOException {
        System.out.println("createServerSocket : host is : " + host + "    port is : " + port);
        return new ServerSocket(this.port, 5, InetAddress.getByName(this.host));
    }
}
