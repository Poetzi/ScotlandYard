package com.example.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.example.server.game.boardGameEngine.implementation.BoardGameEngineImpl;
import com.example.server.game.boardGameEngine.interfaces.BoardGameEngine;
import com.example.server.game.gameBoard.implementation.GameBoardImpl;
import com.example.server.game.gameBoard.interfaces.GameBoard;
import com.example.server.lobby.implementation.ID;
import com.example.server.lobby.implementation.LobbyImpl;
import com.example.server.lobby.interfaces.Lobby;
import com.example.server.messages.BaseMessage;
import com.example.server.messages.SendLobbyID;
import com.example.server.messages.SendPlayerIDtoClient;
import com.example.server.messages.UsernameMessage;

import java.io.IOException;
import java.util.ArrayList;


public class MyKryoServer {
    //Server Objekt
    private Server server;
    private Callback<BaseMessage> messageCallback;
    public static ArrayList<LobbyImpl> lobbys = new ArrayList<LobbyImpl>();

    public MyKryoServer() {
        server = new Server();
    }

    /**
     * eine Methode um Messageklassen zu registrieren
     * Jede Message klasse muss ein Kind von BaseMessage sein!!!!!!!!
     *
     * @param c Klasse die registriert werden soll
     */
    public void registerClass(Class c) {
        server.getKryo().register(c);
    }

    /**
     * eine Methode um den Server zu starten
     *
     * @throws IOException
     */
    public void start() throws IOException {
        new Thread(() -> {

            server.start();
            try {
                server.bind(Ports.TCP);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();



        // Serverlistener wird hinzugefügt
        server.addListener(new Listener() {

            public void connected(Connection connection) {
                System.out.println("main.java.Server: Jemand ist dem main.java.Server beigetreten: " + connection.getRemoteAddressTCP().getHostString());


            }

            @Override
            public void disconnected(Connection connection) {
                System.out.println("main.java.Server: Jemand hat den main.java.Server verlassen");

            }


            // Die Nachricht die erhalten wird wird über den Callback weitergeleitet
            public void received(Connection connection, Object object) {
                if (messageCallback != null && object instanceof BaseMessage)
                    messageCallback.callback((BaseMessage) object);

                if (object instanceof UsernameMessage) {
                    String name = ((UsernameMessage) object).getUsername();
                    System.out.println("User " + name + " ist beigetreten");

                    UsernameMessage msg = (UsernameMessage)object;
                    BoardGameEngineImpl game = BoardGameEngineImpl.getInstance();

                    if (msg.getPlayerId()==0)
                    {
                        game.setCon0(connection);
                        System.out.println("Connection für Player 0 erstellt");
                    }
                    else if (msg.getPlayerId()==1)
                    {
                        game.setCon1(connection);
                        System.out.println("Connection für Player 1 erstellt");
                    }


                }
            }
        });
    }

    /**
     * Eine Methode um Callbacks zu registrieren
     *
     * @param callback
     */
    public void registerCallback(Callback<BaseMessage> callback) {
        this.messageCallback = callback;
    }

    /**
     * Eine Methode die eine Nachricht an alle Clients schickt
     *
     * @param message
     */
    public void broadcastMessage(BaseMessage message) {
        for (Connection connection : server.getConnections())
            connection.sendTCP(message);
    }


    public static ArrayList<LobbyImpl> getLobby() {
        return lobbys;
    }

    public static void setLobby(ArrayList<LobbyImpl> lobby) {
        MyKryoServer.lobbys = lobby;
    }


}
