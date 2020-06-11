package com.example.server.lobby.implementation;

import com.esotericsoftware.kryo.Kryo;
import com.example.server.game.boardGameEngine.implementation.BoardGameEngineImpl;
import com.example.server.game.boardGameEngine.interfaces.BoardGameEngine;

import com.example.server.game.gameBoard.implementation.GameBoardImpl;
import com.example.server.game.gameBoard.interfaces.GameBoard;

import com.example.server.game.players.implementation.PlayerImpl;
import com.example.server.game.players.interfaces.Player;
import com.example.server.game.players.TravelLog;
import com.example.server.lobby.interfaces.Lobby;
import com.example.server.messages.AskPlayerForTurn;
import com.example.server.messages.TravellogMessage;
import com.example.server.messages.TurnMessage;
import com.example.server.messages.UpdatePlayersPosition;

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

        // Hier wird der boolean für diesen Spieler auf true gesetzt
        // Also es wird auf einen Zug des Spielers gewartet
        waitForPlayersTurn[playerId] = true;

        while (waitForPlayersTurn[playerId]) {
            // Wait for TurnMessage from Player
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Player Id " + playerId);
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
        returnTurnMessage[playerId] = turnMessage;
        waitForPlayersTurn[playerId] = false;
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
        if (allReady == true)
        {
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
