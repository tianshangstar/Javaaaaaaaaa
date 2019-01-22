package com.evan.javaaaaaaaaa.advanced.rmi.server;

import com.evan.javaaaaaaaaa.advanced.rmi.MyRmiClientFactory;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerStartup {
    public static void main(String[] args) throws
            RemoteException {
        // 服务ip
//        String serverIp = "10.107.123.88";
//        // 服务端口
//        int serverPort = 4545;
//
//        MyRmiClientFactory registerFactory = new MyRmiClientFactory(serverIp, serverPort);
        // 注册服务
        Registry registry = LocateRegistry.createRegistry(4545);

        UserHandler userHandler = new UserHandlerImpl();
        // 绑定对象
        registry.rebind("userHandler", userHandler);

//        while (true) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
