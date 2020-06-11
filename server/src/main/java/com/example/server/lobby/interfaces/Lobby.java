package com.example.server.lobby.interfaces;

import com.esotericsoftware.kryo.Kryo;
import com.example.server.game.boardGameEngine.interfaces.BoardGameEngine;
import com.example.server.game.gameBoard.interfaces.GameBoard;
import com.example.server.game.players.TravelLog;
import com.example.server.lobby.implementation.ID;
import com.example.server.messages.TurnMessage;

import java.util.ArrayList;

public interface Lobby {

    void addPlayertoGame(ID id);

    int getPlayerCount();

    void startGame();

    void removePlayerfromGame(ID id);

    boolean isLobbyOpen();

    TurnMessage askPlayerforTurn(int playerId);

    void updatePlayerPositionsToAllClients(int playerId, int toField);

    public ArrayList<ID> getPlayers();

    int getLobbyID();

    void setLobbyID(int lobbyID);

    TurnMessage[] getReturnTurnMessage();

    void setReturnTurnMessage(TurnMessage turnMessage, int playerId);

    BoardGameEngine getGame();

    void setGame(BoardGameEngine game);

    void updateTravellogToAllClients(TravelLog travelLog, int round);

    void updateTicketCount(int playerId, int count, String type);

    public boolean isAllReady();

    public void setAllReady(boolean allReady);

    public int getPlayerReady();

    public void setPlayerReady(int playerReady);
}
