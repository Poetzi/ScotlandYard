package com.example.scotlandyard.client;

import android.util.Log;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.example.scotlandyard.modelLayer.boardGameEngine.interfaces.BoardGameEngine;

public class MyClientListener extends Listener {
    private Client client;
    private BoardGameEngine clientEngine;

    public void init(Client client)
    {
        this.client = client;
    }

    public void connected(Connection connection)
    {
        Log.d("Client: ", "Verbunden mit dem main.java.Server");
    }

    public void disconnected(Connection connection)
    {
        Log.d("Client: ", "Verbindung mit dem main.java.Server wurde getrennt");
    }

    public void received(Connection connection, Object object)
    {

    }
}
