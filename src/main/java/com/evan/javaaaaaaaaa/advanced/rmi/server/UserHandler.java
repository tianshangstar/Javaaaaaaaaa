package com.evan.javaaaaaaaaa.advanced.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserHandler extends Remote {

    public User getUser() throws RemoteException;

}
