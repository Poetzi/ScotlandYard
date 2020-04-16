package com.example.scotlandyard.client;

import android.util.Log;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.example.scotlandyard.modelLayer.boardGameEngine.interfaces.BoardGameEngine;

public class MyClientListener extends Listener {
    private Client client;
    private BoardGameEngine clientEngine;
    private boolean messageReceived = false;


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
        //Is the received packet the same class as PacketMessage.class?
        if(object instanceof Message){
            //Cast it, so we can access the message within.
            Message packet = (Message) object;
            System.out.println("received a message from the host: "+packet.message);

            while(!messageReceived){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Client will now exit.");
            System.exit(0);
            //We have now received the message!
            messageReceived = true;
        }

    }
}
