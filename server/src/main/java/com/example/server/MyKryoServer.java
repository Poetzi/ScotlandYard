package com.example.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.example.server.lobby.implementation.ID;
import com.example.server.lobby.implementation.LobbyImpl;
import com.example.server.lobby.interfaces.Lobby;
import com.example.server.messages.BaseMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class MyKryoServer {
    //Server Objekt
    private Server server;
    private Callback<BaseMessage> messageCallback;
    public static ArrayList<Lobby> lobbys = new ArrayList<Lobby>();

    public MyKryoServer() {
        server = new Server();
    }

    /**
     * eine Methode um Messageklassen zu registrieren
     * Jede Message klasse muss ein Kind von BaseMessage sein!!!!!!!!
     * @param c Klasse die registriert werden soll
     */
    public void registerClass(Class c) {
        server.getKryo().register(c);
    }

    /**
     * eine Methode um den Server zu starten
     * @throws IOException
     */
    public void start() throws IOException {
        server.start();
        server.bind(Ports.TCP);

        // Serverlistener wird hinzugefügt
        server.addListener(new Listener() {

            public void connected(Connection connection) {
                System.out.println("main.java.Server: Jemand ist dem main.java.Server beigetreten: " + connection.getRemoteAddressTCP().getHostString());
                //Erstellen einer Nachricht für den Client
                Message message = new Message();
                //Text wird zugewiesen
                message.message = "You have successfully connected to the server: " + new Date().toString();


                ID id = new ID(connection);
                boolean lobbyFound = false;
                for(Lobby lobby : lobbys){
                    if(lobby.isLobbyOpen()){
                        lobby.addPlayertoGame(id);
                        lobbyFound = true;
                        break;
                    }
                }
                if(!lobbyFound){
                    Lobby lobby = new LobbyImpl();
                    lobby.addPlayertoGame(id);
                    lobbys.add(lobby);
                }

                //Nachricht wird über den Port gesendet.
                connection.sendTCP(message);
            }

            @Override
            public void disconnected(Connection connection) {
                System.out.println("main.java.Server: Jemand hat den main.java.Server verlassen");
            }


            // Die Nachricht die erhalten wird wird über den Callback weitergeleitet
            public void received(Connection connection, Object object) {
                if (messageCallback != null && object instanceof BaseMessage)
                    messageCallback.callback((BaseMessage) object);
            }
        });
    }

    /**
     * Eine Methode um Callbacks zu registrieren
     * @param callback
     */
    public void registerCallback(Callback<BaseMessage> callback) {
        this.messageCallback = callback;
    }

    /**
     * Eine Methode die eine Nachricht an alle Clients schickt
     * @param message
     */
    public void broadcastMessage(BaseMessage message) {
        for (Connection connection : server.getConnections())
            connection.sendTCP(message);
    }


    public static ArrayList<Lobby> getLobby() {
        return lobby;
    }

    public static void setLobby(ArrayList<Lobby> lobby) {
        MyKryoServer.lobby = lobby;
    }


}
