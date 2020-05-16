package com.example.server;

import com.example.server.messages.BaseMessage;
import com.example.server.messages.TextMessage;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MyKryoServer server = new MyKryoServer();
        try {
            server.registerClass(BaseMessage.class);
            server.registerClass(TextMessage.class);
            server.registerCallback(nachrichtvomClient -> {
                if (nachrichtvomClient instanceof TextMessage)
                {
                    TextMessage message = (TextMessage) nachrichtvomClient;
                    System.out.println(message.toString());

                    // Server sendet die Nachricht an alle Clients weiter
                    server.broadcastMessage(message);
                }
            });
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

