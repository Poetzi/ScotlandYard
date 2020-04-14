package com.example.scotlandyard.client;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MyKryoClient client = new MyKryoClient();
        client.connectToServer("localhost");
    }
}
