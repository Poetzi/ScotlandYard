package com.example.server.game.boardGameEngine.implementation;

import com.example.server.game.players.TravelLog;
import com.example.server.game.players.implementation.DetectiveImpl;
import com.example.server.game.players.implementation.MrXImpl;
import com.example.server.game.players.interfaces.Detective;
import com.example.server.game.players.interfaces.MrX;
import com.example.server.game.boardGameEngine.interfaces.BoardGameEngine;
import com.example.server.game.gameBoard.implementation.GameBoardImpl;
import com.example.server.game.gameBoard.interfaces.GameBoard;
import com.example.server.game.players.implementation.PlayerImpl;
import com.example.server.game.players.interfaces.Player;
import com.example.server.game.transitions.implementation.TransitionImpl;
import com.example.server.game.transitions.interfaces.Transition;
import com.example.server.lobby.interfaces.Lobby;
import com.example.server.messages.TurnMessage;
//import com.example.server.messages.TurnMessage;

import java.util.ArrayList;
import java.util.Random;

public class BoardGameEngineImpl implements BoardGameEngine {

    private Player[] players = new Player[6];
    private int numberOfPlayers = 0;
    private int maxRounds = 24;
    private int actualRound;
    private GameBoard gameBoard = new GameBoardImpl();
    private int numberOfFields;
    private Lobby lobby;
    private int mrXId;
    // Singleton
    private static BoardGameEngineImpl boardGameEngine;

    // private Konstruktor
    private BoardGameEngineImpl() {
    }

    // Singleton wird zurückgegeben
    public static BoardGameEngineImpl getInstance() {
        if (BoardGameEngineImpl.boardGameEngine == null) {
            BoardGameEngineImpl.boardGameEngine = new BoardGameEngineImpl();
        }
        return BoardGameEngineImpl.boardGameEngine;
    }

    @Override
    public void addDetektiv(String name, int id) {
        players[id] = new DetectiveImpl(name, id);
        numberOfPlayers++;
    }

    @Override
    public void addMrX(String name, int id) {
        players[id] = new MrXImpl(name, id);
        mrXId = id;
        numberOfPlayers++;
    }

    @Override
    public void setupNewGame() {
        gameBoard.addFieldWithTransition(2, 3, "taxi");
        gameBoard.addFieldWithTransition(2, 7, "bus");
        gameBoard.addFieldWithTransition(2, 7, "ubahn");

        gameBoard.addFieldWithTransition(3, 1, "bus");
        gameBoard.addFieldWithTransition(3, 1, "taxi");
        gameBoard.addFieldWithTransition(3, 5, "taxi");

        gameBoard.addFieldWithTransition(5, 12, "taxi");

        gameBoard.addFieldWithTransition(12, 1, "bus");
        gameBoard.addFieldWithTransition(12, 16, "bus");

        gameBoard.addFieldWithTransition(1, 7, "bus");
        gameBoard.addFieldWithTransition(1, 13, "taxi");
        gameBoard.addFieldWithTransition(1, 12, "bus");

        gameBoard.addFieldWithTransition(7, 6, "ubahn");
        gameBoard.addFieldWithTransition(7, 8, "bus");

        gameBoard.addFieldWithTransition(8, 9, "taxi");

        gameBoard.addFieldWithTransition(6, 9, "taxi");
        gameBoard.addFieldWithTransition(6, 9, "bus");
        gameBoard.addFieldWithTransition(6, 13, "bus");
        gameBoard.addFieldWithTransition(6, 13, "taxi");
        gameBoard.addFieldWithTransition(6, 14, "ubahn");

        gameBoard.addFieldWithTransition(13, 15, "taxi");

        gameBoard.addFieldWithTransition(15, 16, "taxi");
        gameBoard.addFieldWithTransition(15, 14, "taxi");
        gameBoard.addFieldWithTransition(15, 19, "bus");

        gameBoard.addFieldWithTransition(14, 20, "ubahn");
        gameBoard.addFieldWithTransition(14, 25, "taxi");
        gameBoard.addFieldWithTransition(14, 25, "bus");

        gameBoard.addFieldWithTransition(16, 18, "taxi");

        gameBoard.addFieldWithTransition(25, 9, "taxi");
        gameBoard.addFieldWithTransition(25, 21, "taxi");
        gameBoard.addFieldWithTransition(25, 11, "bus");

        gameBoard.addFieldWithTransition(9, 10, "bus");

        gameBoard.addFieldWithTransition(10, 11, "taxi");

        gameBoard.addFieldWithTransition(11, 33, "taxi");
        gameBoard.addFieldWithTransition(11, 22, "taxi");

        gameBoard.addFieldWithTransition(33, 32, "bus");

        gameBoard.addFieldWithTransition(32, 23, "taxi");
        gameBoard.addFieldWithTransition(32, 22, "bus");
        gameBoard.addFieldWithTransition(32, 22, "ubahn");

        gameBoard.addFieldWithTransition(22, 21, "bus");
        gameBoard.addFieldWithTransition(22, 21, "ubahn");
        gameBoard.addFieldWithTransition(22, 24, "bus");

        gameBoard.addFieldWithTransition(21, 20, "bus");
        gameBoard.addFieldWithTransition(21, 20, "ubahn");
        gameBoard.addFieldWithTransition(21, 34, "bus");

        gameBoard.addFieldWithTransition(20, 19, "taxi");
        gameBoard.addFieldWithTransition(20, 19, "ubahn");
        gameBoard.addFieldWithTransition(20, 26, "ubahn");

        gameBoard.addFieldWithTransition(19, 18, "bus");
        gameBoard.addFieldWithTransition(19, 18, "ubahn");
        gameBoard.addFieldWithTransition(19, 27, "taxi");

        gameBoard.addFieldWithTransition(23, 44, "taxi");
        gameBoard.addFieldWithTransition(23, 24, "taxi");

        gameBoard.addFieldWithTransition(24, 45, "taxi");
        gameBoard.addFieldWithTransition(45, 44, "taxi");

        gameBoard.addFieldWithTransition(45, 34, "taxi");
        gameBoard.addFieldWithTransition(45, 30, "taxi");

        gameBoard.addFieldWithTransition(34, 29, "taxi");
        gameBoard.addFieldWithTransition(34, 26, "taxi");
        gameBoard.addFieldWithTransition(27, 26, "taxi");

        gameBoard.addFieldWithTransition(28, 29, "bus");
        gameBoard.addFieldWithTransition(30, 29, "taxi");

        gameBoard.addFieldWithTransition(30, 36, "ubahn");
        gameBoard.addFieldWithTransition(30, 31, "bus");
        gameBoard.addFieldWithTransition(31, 44, "taxi");
        gameBoard.addFieldWithTransition(31, 35, "bus");


        gameBoard.addFieldWithTransition(37, 29, "bus");

        gameBoard.addFieldWithTransition(37, 36, "taxi");
        gameBoard.addFieldWithTransition(37, 38, "taxi");
        gameBoard.addFieldWithTransition(38, 36, "taxi");
        gameBoard.addFieldWithTransition(36, 35, "ubahn");

        gameBoard.addFieldWithTransition(43, 35, "taxi");
        gameBoard.addFieldWithTransition(43, 35, "bus");

        gameBoard.addFieldWithTransition(43, 42, "taxi");

        gameBoard.addFieldWithTransition(41, 42, "bus");

        gameBoard.addFieldWithTransition(41, 40, "bus");

        gameBoard.addFieldWithTransition(38, 39, "bus");
        gameBoard.addFieldWithTransition(40, 39, "bus");


    }

    @Override
    public void startGame() {

        // toDo send initial position of the players to clients
        gameBoard.setPositionOfPlayer(0,2);
        gameBoard.setPositionOfPlayer(1, 21);

        lobby.updatePlayerPositionsToAllClients(0,2);
        lobby.updatePlayerPositionsToAllClients(1,21);



        // Aktuelle Runde wird auf 0 gesetzt
        actualRound = 0;

        // Runden werden solange ausgeführt bis die Maximale Rundenanzahl erreicht ist
        for (int i = 0; i < maxRounds; i++) {
            playOneRound();


            // Wenn die Detektive gewonnen haben wird der Spielablauf beendet
            if (checkWinningCondition()) {
                break;
            }

            // Die aktuelle Runde wird erhöht
            actualRound++;
        }

    }

    @Override
    public void playOneRound() {
        for (Player p : players) {
            drawForPlayer(p);
        }
    }

    @Override
    public void drawForPlayer(Player player) {
        String card = "Bus";    // Beispielwert
        int fieldToGo = 0;
        boolean drawValide = false;
        TurnMessage turnMessage;

        // Schleife wird solange ausgeführt bis en gültiger Zug vom Spieler kommt
        while (drawValide == false) {
            /*
               Der Server holt sich vom Spieler Client die Karte die er einsetzen will
               und die Position zu der er ziehen möchte
            */
            turnMessage = lobby.askPlayerforTurn(player.getId());
            card = turnMessage.getCard();
            fieldToGo = turnMessage.getToField();

            /*
               Die Daten vom Zug des Spielers werden weitergegeben an das Gameboard wo überprüft wird,
               ob der Zug gültig ist.
               Wenn der Zug nicht gültig ist wird ein neuer Zug vom Spieler abgefragt.
            */

        }

        /*
            Dem Spieler muss die verwendete Karte noch aus seinen verfügbaren Karten entfernt werden
         */
        Transition toRemove = new TransitionImpl();
        toRemove.setName(card);
        player.removeTransitionFromAvailable(toRemove);




        /*
            Wenn der Zug gültig ist, wird die Positon des Spielers auf dem Gameboard gesetzt
            und an die anderen Spieler Clients weitergegeben
         */
        if (drawValide) {
            gameBoard.setPositionOfPlayer(player.getId(), fieldToGo);

            /*
                Die Position an die anderen Spieler clients weitergeben
             */
            lobby.updatePlayerPositionsToAllClients(player.getId(), fieldToGo);
        }
    }


    @Override
    public boolean checkWinningCondition() {
        int misterX = mrXId;

        if (maxRounds == actualRound) {
            System.out.println("Mister X won");
            return false;
        }

        for (int i = 0; i < numberOfPlayers; i++) {
            /*if(players.get(misterX) == players.get(i).currentPosition){ //Missing field from Player CLass
                System.out.println("Mister X lost");
                return true;
            }
             */
        }
        return false;
    }


    public void initLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    @Override
    public boolean checkIfMrXCheated() {
        return false;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }
}
