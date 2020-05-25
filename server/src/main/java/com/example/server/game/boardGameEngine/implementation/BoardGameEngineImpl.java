package com.example.server.game.boardGameEngine.implementation;



import com.example.server.game.boardGameEngine.interfaces.BoardGameEngine;
import com.example.server.game.gameBoard.implementation.GameBoardImpl;
import com.example.server.game.gameBoard.interfaces.GameBoard;
import com.example.server.game.players.implementation.PlayerImpl;
import com.example.server.game.players.interfaces.Player;
import com.example.server.game.transitions.implementation.TransitionImpl;
import com.example.server.game.transitions.interfaces.Transition;
import com.example.server.lobby.interfaces.Lobby;
//import com.example.server.messages.TurnMessage;

import java.util.ArrayList;
import java.util.Random;

public class BoardGameEngineImpl implements BoardGameEngine {

    private ArrayList<Player> players;
    private int numberOfPlayers;
    private int maxRounds;
    private int actualRound;
    private GameBoard gameBoard;
    private int numberOfFields;
    private Lobby lobby;


    @Override
    public void addPlayer(String name, int field) {
        Player player = new PlayerImpl();
        player.setId(players.size());
        player.setName(name);
        players.add(player);

        gameBoard.setStartField(players.size(),field);
    }

    @Override
    public void setupNewGame() {
        gameBoard = new GameBoardImpl();
        Random rnd = new Random();

        ArrayList<String> lobbyPlayerNames = new ArrayList<>();

        numberOfPlayers = lobbyPlayerNames.size();

        for (int i = 0; i < numberOfPlayers - 1 ; i++) {
            addPlayer(lobbyPlayerNames.get(i),rnd.nextInt(20));
        }
        addPlayer("Mister X",rnd.nextInt(20));

        for (int i = 0; i < numberOfFields ; i++) {
            gameBoard.addField(i);
        }

        for (int i = 0; i < numberOfFields/2 ; i++) {
            int random = rnd.nextInt(20);

            //gameBoard.addFieldWithTransition(random,random,);
        }
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
        TurnMessage turnMessage;

        // Schleife wird solange ausgeführt bis en gültiger Zug vom Spieler kommt
        while (drawValide = false)
        {
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
            if (gameBoard.checkDraw(player.getId(),fieldToGo,card))
            {
                drawValide = true;
            }
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
        if (drawValide)
        {
            gameBoard.setPositionOfPlayer(player.getId(), fieldToGo);

            /*
                Die Position an die anderen Spieler clients weitergeben
             */
            lobby.updatePlayerPositionsToAllClients(player.getId(), fieldToGo);
        }

    }



    @Override
    public boolean checkWinningCondition() {
        int misterX = players.size() - 1;

        if(maxRounds==actualRound){
            System.out.println("Mister X won");
            return false;
        }

        for (int i = 0; i <numberOfPlayers ; i++) {
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
    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }
}
