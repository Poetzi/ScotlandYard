package com.example.scotlandyard.modelLayer.boardGameEngine.implementation;

import com.example.scotlandyard.client.TurnMessage;
import com.example.scotlandyard.modelLayer.boardGameEngine.interfaces.BoardGameEngine;
import com.example.scotlandyard.modelLayer.boardGameEngine.interfaces.Rule;
import com.example.scotlandyard.modelLayer.boardGameEngine.interfaces.WinningCondition;
import com.example.scotlandyard.modelLayer.gameBoard.interfaces.GameBoard;
import com.example.scotlandyard.modelLayer.players.implementation.DetectiveImpl;
import com.example.scotlandyard.modelLayer.players.implementation.MrXImpl;
import com.example.scotlandyard.modelLayer.players.interfaces.Player;
import com.example.scotlandyard.modelLayer.transitions.interfaces.Transition;

import java.util.ArrayList;

public class BoardGameEngineImpl implements BoardGameEngine {

    private ArrayList<Player> players;
    private int numberOfPlayers;
    private int maxRounds;
    private int actualRound;
    private Player clientPlayer;
    private GameBoard gamBoard;
    private WinningCondition winningCondition;
    private ArrayList<Rule> rules;


    @Override
    public void addPlayer(String name) {

    }

    @Override
    public TurnMessage getTurnFromPlayer() {
        TurnMessage turnMessage = new TurnMessage();
        if (clientPlayer instanceof DetectiveImpl)
        {
            /*
            ToDo
            Wenn der Spieler ein Detective ist, nach einem Zug für einen Detective fragen
             */
        }
        else if(clientPlayer instanceof MrXImpl)
        {
            /*
            ToDo
            Wenn der Spieler MrX ist, nach einem Zug für MrX fragen
             */
        }
        else
        {
            // Fehlerausgabe
        }
        return null;
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
