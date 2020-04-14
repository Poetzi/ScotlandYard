package com.example.scotlandyard.client;

import android.util.Log;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class MyClientListener extends Listener {
    private Client client;

    public void init(Client client)
    {
        this.client = client;
    }

    public void connected(Connection connection)
    {
        Log.d("Client: ", "Verbunden mit dem Server");
    }

    public void disconnected(Connection connection)
    {
        Log.d("Client: ", "Verbindung mit dem Server wurde getrennt");
    }

    public void received(Connection connection, Object object)
    {

    }
}
