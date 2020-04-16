package com.example.scotlandyard.client;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        MyKryoClient client = new MyKryoClient();
        client.startClient();
        try {
            client.connectToServer("se2-demo.aau.at");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
