package com.example.scotlandyard.client;

import java.io.IOException;

public class MyNetworkThread extends Thread {
    private MyKryoClient client;


    public MyNetworkThread() {
        this.client = new MyKryoClient();
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
