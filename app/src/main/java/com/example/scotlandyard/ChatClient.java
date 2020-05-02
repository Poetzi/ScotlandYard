package com.example.scotlandyard;

import android.widget.TextView;

import com.example.scotlandyard.client.MyKryoClient;
import com.example.scotlandyard.client.MyNetworkThread;

import java.io.IOException;

public class ChatClient extends Thread {
    private MyKryoClient client;
    private TextView textView;

    public ChatClient(TextView textView){
        client = new MyKryoClient();
        client.setChatClient(this);
        this.textView = textView;
    }

    public void run() {
        client.startClient();
        try {
            client.connectToServer("se2-demo.aau.at");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String nachricht) {
        client.sendMessages(nachricht);
    }

    public void anzeigen(String nachricht){
        textView.setText(nachricht);

    }



}
