package com.example.server;

import com.example.server.game.boardGameEngine.implementation.BoardGameEngineImpl;
import com.example.server.game.boardGameEngine.interfaces.BoardGameEngine;
import com.example.server.game.players.TravelLog;
import com.example.server.lobby.implementation.ID;
import com.example.server.lobby.implementation.LobbyImpl;
import com.example.server.lobby.interfaces.Lobby;

import com.example.server.messages.AskPlayerForTurn;
import com.example.server.messages.BaseMessage;
import com.example.server.messages.ReadyMessage;
import com.example.server.messages.SendLobbyID;
import com.example.server.messages.SendPlayerIDtoClient;
import com.example.server.messages.SendRoleMessage;
import com.example.server.messages.StartGameMessage;
import com.example.server.messages.TextMessage;
import com.example.server.messages.TravellogMessage;
import com.example.server.messages.TurnMessage;
import com.example.server.messages.UpdatePlayersPosition;
import com.example.server.messages.UpdateTicketCount;
import com.example.server.messages.UsernameMessage;

import java.io.IOException;

public class Main {



    public static void main(String[] args) {



        MyKryoServer server = new MyKryoServer();
        BoardGameEngineImpl game = BoardGameEngineImpl.getInstance();
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
            server.registerClass(StartGameMessage.class);
            server.registerClass(SendRoleMessage.class);
            server.registerClass(SendLobbyID.class);
            server.registerClass(SendPlayerIDtoClient.class);
            server.registerClass(ReadyMessage.class);
            server.registerClass(UpdateTicketCount.class);
            server.registerClass(TravelLog.class);


            // Die Callbacks werden hier registriert,
            server.registerCallback(nachrichtvomClient -> {
                // hier wird dan definiert was passieren soll,
                // wenn der Server eine Nachricht vom Client erhält
                if (nachrichtvomClient instanceof TextMessage) {
                    TextMessage message = (TextMessage) nachrichtvomClient;
                    System.out.println(message.toString());
                    if (message.getText().contains("DONE")){
                        game.startGame();
                    }else {
                        // Server sendet die Nachricht an alle Clients weiter
                        server.broadcastMessage(message);
                    }
                }

                if (nachrichtvomClient instanceof TurnMessage) {
                    TurnMessage turn = (TurnMessage) nachrichtvomClient;

                    // Zug wird am Server ausgegeben
                    System.out.println("Looby: " + turn.getLobbyId() + " Spieler " + turn.getPlayerId() + " to Field "
                            + turn.getToField() + " with " + turn.getCard());

                    // Der Zug wird an die Lobby weitergegeben
                    MyKryoServer.getLobby().get(turn.getLobbyId()).setReturnTurnMessage(turn, turn.getPlayerId());

                }

                if (nachrichtvomClient instanceof SendRoleMessage)
                {
                    SendRoleMessage msg = (SendRoleMessage)nachrichtvomClient;

                    if (msg.getText().equals("MISTERX"))
                    {
                        game.addMrX(msg.getName(),msg.getPlayerId());
                        System.out.println("Spieler "+ msg.getName()+" ist ein MrX und wurde erstellt");
                    }
                    if(msg.getText().equals("DETEKTIV"))
                    {
                        game.addDetektiv(msg.getName(), msg.getPlayerId());
                        System.out.println("Spieler "+ msg.getName()+" ist ein Detektiv und wurde erstellt");
                    }

                }

                if(nachrichtvomClient instanceof ReadyMessage)
                {
                    // Die Anzahl der Spieler die bereit sind wird erhöht
                    game.getLobby().setPlayerReady(game.getLobby().getPlayerReady()+1);

                    if (game.getLobby().getPlayerReady()==2)
                    {
                        game.getLobby().setAllReady(true);
                        System.out.println("Spiel beginnt!!");
                    }
                }


            });

            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
