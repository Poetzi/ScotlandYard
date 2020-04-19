package com.example.scotlandyard.modelLayer.boardGameEngine.implementation;

import com.example.scotlandyard.modelLayer.boardGameEngine.interfaces.BoardGameEngine;
import com.example.scotlandyard.modelLayer.boardGameEngine.interfaces.Rule;
import com.example.scotlandyard.modelLayer.boardGameEngine.interfaces.WinningCondition;
import com.example.scotlandyard.modelLayer.gameBoard.interfaces.GameBoard;
import com.example.scotlandyard.modelLayer.players.interfaces.Player;
import com.example.scotlandyard.modelLayer.transitions.interfaces.Transition;

import java.util.ArrayList;

public class BoardGameEngineImpl implements BoardGameEngine {

    private ArrayList<Player> players;
    private int numberOfPlayers;
    private int maxRounds;
    private int actualRound;
    private Player adminPlayer;
    private GameBoard gamBoard;
    private WinningCondition winningCondition;
    private ArrayList<Rule> rules;


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

    @Override
    public boolean checkRules() {
        return false;
    }
}
