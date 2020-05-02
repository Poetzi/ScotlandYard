package com.example.server;

import com.esotericsoftware.kryonet.Connection;

import com.esotericsoftware.kryonet.Listener;

import java.util.ArrayList;
import java.util.Date;

public class MyServerListener extends Listener {
    public ArrayList<String> chatverlauf = new ArrayList();

    public void connected(Connection connection) {
        System.out.println("main.java.Server: Jemand ist dem main.java.Server beigetreten: " + connection.getRemoteAddressTCP().getHostString());
        //Erstellen einer Nachricht für den Client
        Message message = new Message();
        //Text wird zugewiesen
        message.message = "You have successfully connected to the server: " + new Date().toString();

        //Nachricht wird über den Port gesendet.
        connection.sendTCP(message);
    }

    @Override
    public void disconnected(Connection connection) {
        System.out.println("main.java.Server: Jemand hat den main.java.Server verlassen");
    }

    public void received(Connection connection, Object object) {

        if(object instanceof Message)
        {
            Message message = (Message) object;
            String buffer = "";
            buffer = buffer + message.message;
            chatverlauf.add(buffer);
            String chat = "";
            for (int i = 0; i < chatverlauf.size(); i++) {
                chat = chat + chatverlauf.get(i) + "\n";
            }
            connection.sendTCP(chat);
        }

    }
}
