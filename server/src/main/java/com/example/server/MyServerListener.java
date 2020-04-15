package com.example.server;

import com.esotericsoftware.kryonet.Connection;

import com.esotericsoftware.kryonet.Listener;

public class MyServerListener extends Listener {

    public void connected(Connection connection)
    {
        System.out.println("main.java.Server: Jemand ist dem main.java.Server beigetreten");
    }

    @Override
    public void disconnected(Connection connection) {
        System.out.println("main.java.Server: Jemand hat den main.java.Server verlassen");
    }

    public void received(Connection connection, Object object)
    {

    }
}
