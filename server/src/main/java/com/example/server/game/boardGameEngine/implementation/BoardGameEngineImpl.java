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
    private GameBoard gamBoard;




    @Override
    public void addPlayer(String name) {

    }

    @Override
    public void setupNewGame() {

    }

    @Override
    public void startGame() {

        // Runden werden solange ausgeführt bis die Maximale Rundenanzahl erreicht ist
        for (int i = 0; i < maxRounds ; i++) {
            playOneRound();

            // Wenn die Detektive gewonnen haben wird der Spielablauf beendet
            if (checkWinningCondition())
            {
                break;
            }
        }

    }

    @Override
    public void playOneRound() {
        for (Player p:players) {
            drawForPlayer(p);
        }

    }

    @Override
    public void drawForPlayer(Player player) {
        String card = "Bus";    // Beispielwert
        int fieldToGo = 0;
        boolean drawValide = false;

        // Schleife wird solange ausgeführt bis en gültiger Zug vom Spieler kommt
        while (drawValide = false)
        {
            /*
               TODO
               Der Server holt sich vom Spieler Client die Karte die er einsetzen will
               und die Position zu der er ziehen möchte
            */



            /*
               Die Daten vom Zug des Spielers werden weitergegeben an das Gameboard wo überprüft wird,
               ob der Zug gültig ist.
               Wenn der Zug nicht gültig ist wird ein neuer Zug vom Spieler abgefragt.
            */
            if (gamBoard.checkDraw(player.getId(),fieldToGo,card))
            {
                drawValide = true;
            }
        }




        /*
            Wenn der Zug gültig ist, wird die Positon des Spielers auf dem Gameboard gesetzt
            und an die anderen Spieler weitergegeben
         */

    }



    @Override
    public boolean checkWinningCondition() {
        return false;
    }

    
}
