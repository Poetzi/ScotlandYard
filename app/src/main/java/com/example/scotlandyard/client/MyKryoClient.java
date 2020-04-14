package com.example.scotlandyard.client;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

import java.io.IOException;
import java.net.InetAddress;

public class MyKryoClient {
    private Client client;
    private MyClientListener listener;
    private Kryo kryo;

    public MyKryoClient() {
        client = new Client();
        listener = new MyClientListener();
        client.addListener(listener);
        listener.init(client);
        kryo = client.getKryo();
        registerMessages();
    }


    public void connectToServer(String host) throws IOException {
        client.start();
        client.connect(5000, host, Ports.TCP);
    }

    public void stopClient() {
        client.stop();
    }

    private void registerMessages() {

    }

}

