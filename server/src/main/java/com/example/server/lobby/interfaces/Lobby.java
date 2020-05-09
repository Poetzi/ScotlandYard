package com.example.server.lobby.interfaces;

import com.esotericsoftware.kryo.Kryo;
import com.example.server.TurnMessage;
import com.example.server.lobby.implementation.ID;

public interface Lobby {

    void addPlayertoGame(ID id);
    int getPlayerCount();
    void startGame();
    void removePlayerfromGame(ID id);
    boolean isLobbyOpen();

    TurnMessage askPlayerforTurn(int playerId);

    void updatePlayerPositionsToAllClients(int playerId, int toField);
}
