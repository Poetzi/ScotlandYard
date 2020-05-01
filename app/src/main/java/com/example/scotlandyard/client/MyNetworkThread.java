package com.example.scotlandyard.client;

import android.widget.TextView;

import java.io.IOException;

public class MyNetworkThread extends Thread {
    private MyKryoClient client;
    private TextView textView;


    public MyNetworkThread(TextView textView) {
        this.client = new MyKryoClient(textView);
        this.textView = textView;
    }

    public void run()
    {
        client.startClient();
        try {
            client.connectToServer("se2-demo.aau.at");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
