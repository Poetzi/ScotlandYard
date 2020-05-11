package com.example.server;

import com.esotericsoftware.kryonet.Connection;

import com.esotericsoftware.kryonet.Listener;
import com.example.server.lobby.implementation.ID;
import com.example.server.lobby.implementation.LobbyImpl;
import com.example.server.lobby.interfaces.Lobby;

import java.util.Date;

public class MyServerListener extends Listener {
    MyKryoServer server;

    public MyServerListener(MyKryoServer server){
        this.server = server;
    }

    public void connected(Connection connection) {
        System.out.println("main.java.Server: Jemand ist dem main.java.Server beigetreten: " + connection.getRemoteAddressTCP().getHostString());
        //Erstellen einer Nachricht für den Client
        Message message = new Message();
        //Text wird zugewiesen
        message.message = "You have successfully connected to the server: " + new Date().toString();


        ID id = new ID(connection);
        boolean lobbyFound = false;
        for(Lobby lobby : server.lobby){
            if(lobby.isLobbyOpen()){
                lobby.addPlayertoGame(id);
                lobbyFound = true;
                break;
            }
        }
        if(!lobbyFound){
            Lobby lobby = new LobbyImpl();
            lobby.addPlayertoGame(id);
            server.lobby.add(lobby);
        }

        //Nachricht wird über den Port gesendet.
        connection.sendTCP(message);
    }

    @Override
    public void disconnected(Connection connection) {
        System.out.println("main.java.Server: Jemand hat den main.java.Server verlassen");
    }

    @Override
    public void received(Connection connection, Object object) {

    }
}
