package com.example.server.lobby.implementation;

import com.example.server.game.boardGameEngine.implementation.BoardGameEngineImpl;

public class LobbyStart implements Runnable {

    LobbyImpl lobby;

    public LobbyStart(LobbyImpl lobby) {
        this.lobby = lobby;
    }

    @Override
    public void run() {
        BoardGameEngineImpl boardgame = BoardGameEngineImpl.getInstance();
        boardgame.setLobby(lobby);
        boardgame.setupNewGame();


    }
}
