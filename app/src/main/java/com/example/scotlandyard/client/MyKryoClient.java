package com.example.scotlandyard.client;

import android.widget.TextView;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.example.scotlandyard.ChatClient;
import com.example.scotlandyard.modelLayer.boardGameEngine.interfaces.BoardGameEngine;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

public class MyKryoClient {
    //Client objekt
    private Client client;
    private MyClientListener listener;
    private Kryo kryo;
    private TextView textView;
    private ChatClient chatClient;


    public MyKryoClient(TextView textView){
        this.textView = textView;
        client = new Client();
        listener = new MyClientListener();
        listener.setTextView(textView);
        client.addListener(listener);
        listener.init(this);
        kryo = client.getKryo();
        registerMessages();
    }

    public MyKryoClient() {
        client = new Client();
        listener = new MyClientListener();
        client.addListener(listener);
        listener.init(this);
        kryo = client.getKryo();
        registerMessages();
    }

    public void setChatClient(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    public ChatClient getChatClient() {
        return chatClient;
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

    private void registerMessages() {
        kryo.register(Message.class);
    }

    public void sendMessages(String nachricht){
        //Nachricht wird über den Port gesendet.
        client.sendTCP(nachricht);
    }

}

