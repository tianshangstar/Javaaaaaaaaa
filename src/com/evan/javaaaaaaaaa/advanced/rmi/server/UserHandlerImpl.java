package com.evan.javaaaaaaaaa.advanced.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class UserHandlerImpl extends UnicastRemoteObject implements UserHandler {

    protected UserHandlerImpl() throws RemoteException {
    }

    protected UserHandlerImpl(int port) throws RemoteException {
        super(port);
    }

    protected UserHandlerImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public User getUser() throws RemoteException {
        User user = new User();
        user.setUserName("abc");
        user.setAge(999);
        user.setSex("妖怪");
        return user;
    }
}
