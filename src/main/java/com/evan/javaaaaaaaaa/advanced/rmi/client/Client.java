package com.evan.javaaaaaaaaa.advanced.rmi.client;

import com.evan.javaaaaaaaaa.advanced.rmi.server.UserHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {

        //-Djava.net.preferIPv4Stack=true windows默认ipv6，增加jvm启动参数
        UserHandler userHandler = (UserHandler) Naming.lookup("rmi://10.107.66.142:4545/userHandler");

        System.out.println(userHandler.getUser());

    }
}
