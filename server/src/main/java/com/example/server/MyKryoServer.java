package com.example.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.example.server.lobby.implementation.ID;
import com.example.server.lobby.implementation.LobbyImpl;
import com.example.server.lobby.interfaces.Lobby;

import java.io.IOException;
import java.util.ArrayList;

public class MyKryoServer {
    //Server Objekt
    private Server server;
    private MyServerListener listener;
    private Kryo kryo;
    public static ArrayList<Lobby> lobby = new ArrayList<Lobby>();

    public MyKryoServer() {
        System.out.println("Server is starting...");
        //Server wird erstellt
        server = new Server();
        //listener wird hinzugefügt
        listener = new MyServerListener(this);
        //Bindet KryoServer für einfacheres handling
        kryo = server.getKryo();
        registerClasses();
    }

    public void startServer() throws IOException {
        server.start();
        server.bind(Ports.TCP);
        server.addListener(listener);
    }

    private void registerClasses()
    {
        //Package Klasse wird registriert. (Es können nur Objekte
        //gesendet werden, die registriert sind.
        kryo.register(Message.class);
        kryo.register(ID.class);

    }

    public void stopServer()
    {
        server.stop();
    }
}
