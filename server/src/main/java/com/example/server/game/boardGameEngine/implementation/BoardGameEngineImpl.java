package com.example.server.game.boardGameEngine.implementation;



import com.example.server.game.boardGameEngine.interfaces.BoardGameEngine;
import com.example.server.game.gameBoard.implementation.GameBoardImpl;
import com.example.server.game.gameBoard.interfaces.GameBoard;
import com.example.server.game.players.implementation.PlayerImpl;
import com.example.server.game.players.interfaces.Detective;
import com.example.server.game.players.interfaces.MrX;
import com.example.server.game.players.interfaces.Player;
import com.example.server.game.transitions.interfaces.Transition;

import java.util.ArrayList;
import java.util.Random;

public class BoardGameEngineImpl implements BoardGameEngine {

    private ArrayList<Player> players;
    private int numberOfPlayers;
    private int maxRounds;
    private int actualRound;
    private GameBoard gameBoard;
    private int numberOfFields;


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

            gameBoard.addFieldWithTransition(random,random,);
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
            if (gameBoard.checkDraw(player.getId(),fieldToGo,card))
            {
                drawValide = true;
            }
        }

        /*
            TODO
            Dem Spieler muss die verwendete Karte noch aus seinen verfügbaren Karten entfernt werden
         */
            if (player instanceof Detective){
                int ticketType;
                switch (card){
                    case "Taxi":
                        ticketType=1;
                        break;
                    case "Bus":
                        ticketType=2;
                        break;
                    case "U-Bahn":
                        ticketType=3;
                        break;
                    default:
                        ticketType=0;
                        break;
                }
                ((Detective) player).validateTicket(ticketType);
            }else if (player instanceof MrX){
                int ticketType;
                switch (card){
                    case "Taxi":
                        ticketType=1;
                        break;
                    case "Bus":
                        ticketType=2;
                        break;
                    case "U-Bahn":
                        ticketType=3;
                        break;
                    case "Black":
                        ticketType=4;
                        break;
                    case "Double":
                        ticketType=5;
                        break;
                    default:
                        ticketType=0;
                        break;
                }
                ((MrX) player).validateTicket(actualRound,ticketType,fieldToGo);
            }



        /*
            Wenn der Zug gültig ist, wird die Positon des Spielers auf dem Gameboard gesetzt
            und an die anderen Spieler Clients weitergegeben
         */
        if (drawValide)
        {
            gameBoard.setPositionOfPlayer(player.getId(), fieldToGo);

            /*
                TODO
                Die Position an die anderen Spieler clients weitergeben
             */
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
            if(players.get(misterX).getCurrentPosition() == players.get(i).getCurrentPosition()){ //Missing field from Player CLass
                System.out.println("Mister X lost");
                return true;
            }
        }
        return false;
    }

    
}
