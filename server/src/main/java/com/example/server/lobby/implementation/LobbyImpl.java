package com.example.server.lobby.implementation;

import com.esotericsoftware.kryo.Kryo;
import com.example.server.lobby.interfaces.Lobby;
import com.example.server.messages.AskPlayerForTurn;
import com.example.server.messages.TurnMessage;
import com.example.server.messages.UpdatePlayersPosition;

import java.util.ArrayList;

public class LobbyImpl implements Lobby {
    private ArrayList<ID> players = new ArrayList<ID>();
    private boolean isOpen = false;
    public int playerCount =0;

    //ToDo
    private   int lobbyID = 1;

    // Speichert ob auf einen Zug für einen Spieler gewartet wird
    private boolean[] waitForPlayersTurn = new boolean[6];


    @Override
    public void addPlayertoGame(ID id) {
        players.add(id);
        playerCount++;
        if(playerCount==6) startGame();
    }

    @Override
    public int getPlayerCount() {
        return playerCount;
    }



    @Override
    public void startGame() {
        Runnable runnable =    new Runnable(){
            public void run(){

            }
        };
        Thread t = new Thread(runnable);
        t.start();

    }

    @Override
    public void removePlayerfromGame(ID id) {
        players.remove(id);
    }

    @Override
    public boolean isLobbyOpen(){
        return isOpen;
    }

    @Override
    public TurnMessage askPlayerforTurn(int playerId) {

        AskPlayerForTurn askPlayerForTurn = new AskPlayerForTurn(playerId,"gib bitte einen Zug an", lobbyID);
        players.get(playerId).name.sendTCP(askPlayerForTurn);

        // Hier wird der boolean für diesen Spieler auf true gesetzt
        // Also es wird auf einen Zug des Spielers gewartet
        waitForPlayersTurn[playerId] = true;

        while(waitForPlayersTurn[playerId])
        {
            // Wait for TurnMessage from Player
        }
        return null;
    }


    @Override
    public void updatePlayerPositionsToAllClients(int playerId, int toField) {
        UpdatePlayersPosition playersPosition = new UpdatePlayersPosition(playerId, toField, lobbyID);
        for(ID p: players)
        {
            p.name.sendTCP(playersPosition);
        }
    }


    public int getLobbyID() {
        return lobbyID;
    }

    public void setLobbyID(int lobbyID) {
        this.lobbyID = lobbyID;
    }
}
