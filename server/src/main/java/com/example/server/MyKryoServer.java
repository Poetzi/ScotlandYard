package com.example.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class MyKryoServer {
    //Server Objekt
    private Server server;
    private MyServerListener listener;
    private Kryo kryo;

    public MyKryoServer() {
        System.out.println("Server is starting...");
        //Server wird erstellt
        server = new Server();
        //listener wird hinzugefügt
        listener = new MyServerListener();
        //Bindet KryoServer für einfacheres handling
        kryo = server.getKryo();
        registerMessages();
    }

    public void startServer() throws IOException {
        server.start();
        server.bind(Ports.TCP);
        server.addListener(listener);
    }

    private void registerMessages()
    {
        //Package Klasse wird registriert. (Es können nur Objekte
        //gesendet werden, die registriert sind.
        kryo.register(Message.class);

    }

    public void stopServer()
    {
        server.stop();
    }
}
