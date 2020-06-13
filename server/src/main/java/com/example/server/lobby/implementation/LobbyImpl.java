package com.example.server.lobby.implementation;

import com.example.server.game.boardGameEngine.implementation.BoardGameEngineImpl;
import com.example.server.game.boardGameEngine.interfaces.BoardGameEngine;
import com.example.server.game.players.TravelLog;
import com.example.server.lobby.interfaces.Lobby;
import com.example.server.messages.AskPlayerForTurn;
import com.example.server.messages.TravellogMessage;
import com.example.server.messages.TurnMessage;
import com.example.server.messages.UpdatePlayersPosition;
import com.example.server.messages.UpdateTicketCount;

import java.util.ArrayList;

public class LobbyImpl implements Lobby {


    private ArrayList<ID> players = new ArrayList<ID>();
    private boolean isOpen = true;
    public int playerCount = 0;

    private boolean allReady;
    private int playerReady = 0;


    private BoardGameEngine game = BoardGameEngineImpl.getInstance();

    // ToDo
    private int lobbyID = 1;

    // Speichert ob auf einen Zug für einen Spieler gewartet wird
    private boolean[] waitForPlayersTurn = new boolean[6];
    private TurnMessage[] returnTurnMessage = new TurnMessage[6];


    @Override
    public ArrayList<ID> getPlayers() {
        return players;
    }

    public boolean wait = true;


    @Override
    public void addPlayertoGame(ID id) {
        players.add(id);
        playerCount++;
        if (playerCount == 2)
            startGame();// was 6
    }

    @Override
    public int getPlayerCount() {
        return playerCount;
    }

    @Override
    public void startGame() {

        Runnable runnable = new LobbyStart(this);

        game.initLobby(this);

        Thread t = new Thread(runnable);
        t.start();

    }

    @Override
    public void removePlayerfromGame(ID id) {
        players.remove(id);
    }

    @Override
    public boolean isLobbyOpen() {
        return isOpen;
    }

    @Override
    public TurnMessage askPlayerforTurn(int playerId) {
        AskPlayerForTurn askPlayerForTurn = new AskPlayerForTurn(playerId, "gib bitte einen Zug an", lobbyID);
        players.get(playerId).name.sendTCP(askPlayerForTurn);
        /*waitForPlayersTurn[playerId] = true;
        while(waitForPlayersTurn[playerId]){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        waitForPlayersTurn[playerId] = true;
        System.out.println("took");*/
        return returnTurnMessage[playerId];
    }


    @Override
    public void updatePlayerPositionsToAllClients(int playerId, int toField) {
        UpdatePlayersPosition playersPosition = new UpdatePlayersPosition(playerId, toField, lobbyID);
        for (ID p : players) {
            p.name.sendTCP(playersPosition);
        }
    }

    @Override
    public void updateTravellogToAllClients(TravelLog travelLog, int round) {
        TravellogMessage travellogMessage = new TravellogMessage(travelLog, round, lobbyID);
        for (ID id : players) {
            id.name.sendTCP(travellogMessage);
        }
    }

    @Override
    public void updateTicketCount(int playerId, int count, String type) {
        UpdateTicketCount ticketCount = new UpdateTicketCount(count, type, playerId, lobbyID);
        players.get(playerId).name.sendTCP(ticketCount);
        System.out.println("Ticketcount wurde geschickt.");
    }

    @Override
    public int getLobbyID() {
        return lobbyID;
    }

    @Override
    public void setLobbyID(int lobbyID) {
        this.lobbyID = lobbyID;
    }

    @Override
    public TurnMessage[] getReturnTurnMessage() {
        return returnTurnMessage;
    }

    @Override
    public void setReturnTurnMessage(TurnMessage turnMessage, int playerId) {
        System.out.println(turnMessage.getCard()+"Setttting");
        waitForPlayersTurn[playerId] = false;
        returnTurnMessage[playerId] = turnMessage;
    }

    @Override
    public BoardGameEngine getGame() {
        return game;
    }

    @Override
    public void setGame(BoardGameEngine game) {
        this.game = game;
    }

    @Override
    public boolean isAllReady() {
        return allReady;
    }

    @Override
    public void setAllReady(boolean allReady) {
        this.allReady = allReady;
        if (allReady == true) {
            game.startGame();
        }
    }

    @Override
    public int getPlayerReady() {
        return playerReady;
    }

    @Override
    public void setPlayerReady(int playerReady) {
        this.playerReady = playerReady;
    }
}
