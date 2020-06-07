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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
        //Ticketanzahl zu Beginn anzeigen
        for (Player p:players){
            if (p instanceof Detective){
                lobby.updateTicketCount(p.getId(),11,"taxi");
                lobby.updateTicketCount(p.getId(),8,"bus");
                lobby.updateTicketCount(p.getId(),4,"ubahn");
            }else{
                lobby.updateTicketCount(p.getId(),5,"black");
                lobby.updateTicketCount(p.getId(),2,"doublemove");
            }
        }

        // Aktuelle Runde wird auf 0 gesetzt
        actualRound = 0;

        // Runden werden solange ausgeführt bis die Maximale Rundenanzahl erreicht ist
        for (int i = actualRound; i < maxRounds ; i++) {
            playOneRound();


            // Wenn die Detektive gewonnen haben wird der Spielablauf beendet
            if (checkWinningCondition())
            {
                break;
            }

            // Die aktuelle Runde wird erhöht
            actualRound++;
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
        //Zug wird nur durchgeführt, wenn der Detektiv nicht inaktiv ist oder wenn es sich um den Zug von Mr. X handelt.
        if ((player instanceof Detective && ((Detective) player).isInactive() == false) || player instanceof MrX) {
            String card = "Bus";    // Beispielwert
            int fieldToGo = 0;
            boolean drawValide = false;
            TurnMessage turnMessage;

            // Schleife wird solange ausgeführt bis ein gültiger Zug vom Spieler kommt
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
                if (gameBoard.checkDraw(player.getId(),fieldToGo,card))
                {
                    drawValide = true;
                }
            }

            if (card.equals("cheat")){
                if (player instanceof MrX){
                    cheatMoveMrX(player);
                }else {
                    cheatMoveDetectiv(player);
                }
            }else if (card.equals("DoubleMove")){
                doubleMove(player);
            }else {
                oneMove(player,card,fieldToGo);
            }


        }else {
            ((Detective) player).setInactive(false);
        }
    }

    @Override
    public boolean checkWinningCondition() {
        int misterX = players.size() - 1;

        if (maxRounds == actualRound) {
            System.out.println("Mister X won");
            return false;
        }

        for (int i = 0; i < numberOfPlayers; i++) {
            if (players.get(misterX).getCurrentPosition() == players.get(i).getCurrentPosition()) { // Missing field
                // from Player CLass
                System.out.println("Mister X lost");
                return true;
            }
        }
        return false;
    }


    public void initLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    /**
     * Methode überprüft ob Mr. X in den letzten 5 Zügen mindestens einmal
     * geschummelt hat.
     *
     * @return true, wenn mindestens einmal {@link MrX#getHasCheatedInRound(int)}
     *         true zurück liefert.
     */
    @Override
    public boolean checkIfMrXCheated () {
        int misterX = players.size() - 1;
        MrX mrX = (MrX) players.get(misterX);
        for (int i = actualRound - 5; i <= actualRound; i++) {
            if (mrX.getHasCheatedInRound(i)) {
                return true;
            }
        }
        return false;
    }


    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    public void oneMove(Player player, String card, int fieldToGo){
        /*
            Dem Spieler muss die verwendete Karte noch aus seinen verfügbaren Karten entfernt werden
         */
        Transition toRemove = new TransitionImpl();
        toRemove.setName(card);
        player.removeTransitionFromAvailable(toRemove);

        if (player instanceof Detective){
            ((Detective)player).validateTicket(card);
        }else {
            ((MrX)player).validateTicket(actualRound,card,fieldToGo);
            lobby.updateTravellogToAllClients(((MrX)player).getTravelLog(actualRound),actualRound);
            lobby.updateTicketCount(player.getId(),((MrX)player).getBlackTickets(),"Black");
            lobby.updateTicketCount(player.getId(),((MrX)player).getDoubleMoveTickets(),"DoubleMove");
        }
        lobby.updateTicketCount(player.getId(),player.getBusTickets(),"Bus");
        lobby.updateTicketCount(player.getId(),player.getTaxiTickets(),"Taxi");
        lobby.updateTicketCount(player.getId(),player.getUndergroundTickets(),"U-Bahn");

        player.setCurrentPosition(fieldToGo);
        gameBoard.setPositionOfPlayer(player.getId(),fieldToGo);

    }

    public void doubleMove(Player player){
        /*
            Dem Spieler muss die verwendete Karte noch aus seinen verfügbaren Karten entfernt werden
         */
        Transition toRemove = new TransitionImpl();
        toRemove.setName("DoubleMove");
        player.removeTransitionFromAvailable(toRemove);

        String card = "Bus";    // Beispielwert
        boolean drawValide = false;
        int fieldToGo=0;
        TurnMessage turnMessage;

        // Schleife wird solange ausgeführt bis ein gültiger Zug vom Spieler kommt
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
            if (gameBoard.checkDraw(player.getId(),fieldToGo,card))
            {
                drawValide = true;
            }
        }

        toRemove = new TransitionImpl();
        toRemove.setName(card);
        player.removeTransitionFromAvailable(toRemove);
        ((MrX)player).validateDoubleMoveTicket(actualRound,card,fieldToGo);

        lobby.updateTravellogToAllClients(((MrX)player).getTravelLog(actualRound),actualRound);
        //Ticketanzahl aktualisieren
        lobby.updateTicketCount(player.getId(),((MrX)player).getDoubleMoveTickets(),"DoubleMove");
        lobby.updateTicketCount(player.getId(),player.getBusTickets(),"Bus");
        lobby.updateTicketCount(player.getId(),player.getTaxiTickets(),"Taxi");
        lobby.updateTicketCount(player.getId(),player.getUndergroundTickets(),"U-Bahn");
        lobby.updateTicketCount(player.getId(),((MrX)player).getBlackTickets(),"Black");

        player.setCurrentPosition(fieldToGo);
        gameBoard.setPositionOfPlayer(player.getId(),fieldToGo);

        actualRound++;

        drawForPlayer(player);

    }

    public void cheatMoveDetectiv(Player player){

        boolean cheated=checkIfMrXCheated();
        if (!cheated){
            ((Detective)player).setInactive(true);
        }else {
            Player mrX=players.get(players.size() - 1);
            ((MrX)mrX).setCaughtCheating(true,actualRound);

            drawForPlayer(player);
        }

    }

    public void cheatMoveMrX(Player player){
        ((MrX)player).validateTicket(actualRound,"cheat",0);

        //erster Zug normal
        drawForPlayer(player);

        actualRound++;

        //Zweiter geschummelter Zug
        String card = "Bus";    // Beispielwert
        int fieldToGo = 0;
        boolean drawValide = false;
        TurnMessage turnMessage;

        // Schleife wird solange ausgeführt bis ein gültiger Zug vom Spieler kommt
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
            if (gameBoard.checkDraw(player.getId(),fieldToGo,card))
            {
                drawValide = true;
            }
        }
        player.setCurrentPosition(fieldToGo);
        gameBoard.setPositionOfPlayer(player.getId(),fieldToGo);

        lobby.updateTicketCount(player.getId(),player.getBusTickets(),"Bus");
        lobby.updateTicketCount(player.getId(),player.getTaxiTickets(),"Taxi");
        lobby.updateTicketCount(player.getId(),player.getUndergroundTickets(),"U-Bahn");
        lobby.updateTicketCount(player.getId(),((MrX)player).getBlackTickets(),"Black");

    }
}
