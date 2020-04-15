package com.example.server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MyKryoServer server = new MyKryoServer();
        try {
            server.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
