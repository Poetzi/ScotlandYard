package com.example.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.example.server.lobby.implementation.ID;
import com.example.server.lobby.implementation.LobbyImpl;
import com.example.server.lobby.interfaces.Lobby;
import com.example.server.messages.BaseMessage;

import java.io.IOException;
import java.util.ArrayList;

public class MyKryoServer {
    //Server Objekt
    private Server server;
    private Callback<BaseMessage> messageCallback;
    public static ArrayList<Lobby> lobby = new ArrayList<Lobby>();

    public MyKryoServer() {
        server = new Server();
    }

    public void registerClass(Class c) {
        server.getKryo().register(c);
    }

    public void start() throws IOException {
        server.start();
        server.bind(Ports.TCP);

        server.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (messageCallback != null && object instanceof BaseMessage)
                    messageCallback.callback((BaseMessage) object);
            }
        });
    }

    public void registerCallback(Callback<BaseMessage> callback) {
        this.messageCallback = callback;
    }

    public void broadcastMessage(BaseMessage message) {
        for (Connection connection : server.getConnections())
            connection.sendTCP(message);
    }
}
