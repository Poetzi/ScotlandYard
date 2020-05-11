package com.example.scotlandyard.client;

import android.widget.TextView;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

import java.io.IOException;

public class MyKryoClient {
    //Client objekt
    private Client client;
    private MyClientListener listener;
    private Kryo kryo;
    private TextView textView;



    public MyKryoClient(TextView textView){
        this.textView = textView;
        client = new Client();
        listener = new MyClientListener();
        listener.setTextView(textView);
        client.addListener(listener);
        listener.init(client);
        kryo = client.getKryo();
        registerClasses();
    }

    public void startClient()
    {
        new Thread(client).start();
    }

    public void connectToServer(String host) throws IOException {
        client.connect(5000, host, Ports.TCP);
    }

    public void stopClient() {
        client.stop();
    }

    private void registerClasses() {
        kryo.register(Message.class);
        kryo.register(ID.class);
    }

}

