package com.example.server;

import com.example.server.game.boardGameEngine.implementation.BoardGameEngineImpl;
import com.example.server.game.players.TravelLog;

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

                    // Server sendet die Nachricht an alle Clients weiter
                    server.broadcastMessage(message);
                }

                if (nachrichtvomClient instanceof TurnMessage) {
                    TurnMessage turn = (TurnMessage) nachrichtvomClient;

                    // Zug wird am Server ausgegeben
                    System.out.println("Looby: " + turn.getLobbyId() + " Spieler " + turn.getPlayerId() + " to Field "
                            + turn.getToField() + " with " + turn.getCard());

                    // Der Zug wird an die Lobby weitergegeben
                    //game.setTurns(turn, turn.getPlayerId());
                    if (turn.getPlayerId()== 0)
                    {
                        if (game.checkDraw(turn))
                        {
                            System.out.println("Player 0 guter Zug");
                            game.getLobby().updatePlayerPositionsToAllClients(0,turn.getToField());
                            System.out.println("frage Spieler 1 nach Zug");
                            game.getLobby().askPlayerforTurn(1);
                        }
                        else
                        {
                            System.out.println("frage Spieler 0 nach Zug");
                            game.getLobby().askPlayerforTurn(0);
                        }
                    }

                    if (turn.getPlayerId()== 1)
                    {
                        if (game.checkDraw(turn))
                        {
                            System.out.println("Player 1 guter Zug");
                            game.getLobby().updatePlayerPositionsToAllClients(1,turn.getToField());
                            System.out.println("frage Spieler 0 nach Zug");
                            game.getLobby().askPlayerforTurn(0);
                        }
                        else
                        {
                            System.out.println("frage Spieler 1 nach Zug");
                            game.getLobby().askPlayerforTurn(1);
                        }
                    }

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
                    game.playerReady++;
                    System.out.println("Ein Spieler ist bereit");


                    if (game.playerReady==2)
                    {
                        System.out.println("Spiel beginnt!!");
                        game.setupNewGame();
                        System.out.println("Setup Game");
                        game.sendStartingPositions();
                        System.out.println("sende Startpositionen");

                        // Spieler 0 wird nach einem Zug gefragt
                        System.out.println("Spieler 0 wird nach einem Zug gefragt");
                        game.getLobby().askPlayerforTurn(0);

                    }
                }


            });

            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
