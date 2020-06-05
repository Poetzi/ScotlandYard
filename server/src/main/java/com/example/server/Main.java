package com.example.server;

import com.example.server.lobby.implementation.ID;
import com.example.server.lobby.implementation.LobbyImpl;
import com.example.server.lobby.interfaces.Lobby;

import com.example.server.messages.AskPlayerForTurn;

import com.example.server.messages.BaseMessage;
import com.example.server.messages.TextMessage;
import com.example.server.messages.TravellogMessage;
import com.example.server.messages.TurnMessage;
import com.example.server.messages.UpdatePlayersPosition;
import com.example.server.messages.UsernameMessage;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MyKryoServer server = new MyKryoServer();
        try {
            // Registrieren der Messageklassen zur Kommunikation
            // zwischen Server und Client
            server.registerClass(BaseMessage.class);
            server.registerClass(TextMessage.class);
            server.registerClass(TurnMessage.class);
            server.registerClass(AskPlayerForTurn.class);
            server.registerClass(TravellogMessage.class);
            server.registerClass(UsernameMessage.class);
            server.registerClass(UpdatePlayersPosition.class);


            // Die Callbacks werden hier registriert,
            server.registerCallback(nachrichtvomClient -> {
                // hier wird dan definiert was passieren soll,
                // wenn der Server eine Nachricht vom Client erhält
                if (nachrichtvomClient instanceof TextMessage) {
                    TextMessage message = (TextMessage) nachrichtvomClient;
                    System.out.println(message.toString());

                    // Server sendet die Nachricht an alle Clients weiter
                    server.broadcastMessage(message);
                }

                if (nachrichtvomClient instanceof TurnMessage) {
                    TurnMessage turn = (TurnMessage) nachrichtvomClient;

                    // Zug wird am Server ausgegeben
                    System.out.println("Looby: " + turn.getLobbyId() + " Spieler " + turn.getPlayerId() + " to Field "
                            + turn.getToField() + " with " + turn.getCard());

                    // Der Zug wird an die Lobby weitergegeben
                    MyKryoServer.getLobby().get(turn.getLobbyId()).setReturnTurnMessage(turn, turn.getPlayerId());

                }


            });

            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
