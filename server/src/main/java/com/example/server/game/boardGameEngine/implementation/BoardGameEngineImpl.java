package com.example.server.game.boardGameEngine.implementation;



import com.example.server.game.boardGameEngine.interfaces.BoardGameEngine;
import com.example.server.game.gameBoard.interfaces.GameBoard;
import com.example.server.game.players.interfaces.Player;
import com.example.server.game.transitions.interfaces.Transition;

import java.util.ArrayList;

public class BoardGameEngineImpl implements BoardGameEngine {

    private ArrayList<Player> players;
    private int numberOfPlayers;
    private int maxRounds;
    private int actualRound;
    private Player adminPlayer;
    private GameBoard gamBoard;



    @Override
    public void addPlayer(String name) {

    }

    @Override
    public void setupNewGame() {

    }

    @Override
    public void startGame() {

    }

    @Override
    public void playOneRound() {

    }

    @Override
    public void drawForPlayer(Player player) {

    }

    @Override
    public void movePlayer(int playerId, Transition withTransition, int tofield) {

    }

    @Override
    public boolean checkWinningCondition() {
        return false;
    }

    
}
