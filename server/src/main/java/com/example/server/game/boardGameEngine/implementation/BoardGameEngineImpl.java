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
        /*
         * Player player = new PlayerImpl(); player.setId(players.size());
         * player.setName(name); Player player; if (name.equals("Mister X")){ player =
         * new MrXImpl(name,players.size()); }else { player=new
         * DetectiveImpl(name,players.size()); } player.setCurrentPosition(field);
         * players.add(player);
         *
         * gameBoard.setStartField(players.size(),field);
         */
        Player player = new PlayerImpl();
        player.setCurrentPosition(field);
        players.add(player);

        gameBoard.setStartField(players.size(),field);
    }

    @Override
    public void setupNewGame() {
        gameBoard = new GameBoardImpl();
        addPlayer("test", 2);
        Player player = new PlayerImpl();
        player.setCurrentPosition(2);
        player.setId(0);
        players.add(player);

        gameBoard.addFieldWithTransition(2, 3, "taxi");

        gameBoard.setPositionOfPlayer(0, 2);
        drawForPlayer(player);
    }

    @Override
    public void startGame() {

        // Runden werden solange ausgeführt bis die Maximale Rundenanzahl erreicht ist
        for (int i = 0; i < maxRounds; i++) {
            playOneRound();

            // Wenn die Detektive gewonnen haben wird der Spielablauf beendet
            if (checkWinningCondition()) {
                break;
            }
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
        //Zug wird nur durchgeführt, wenn der Detektiv nicht inaktiv ist oder wenn es sich um den Zug von Mr. X handelt.
        if ((player instanceof Detective && ((Detective) player).isInactive() == false) || player instanceof MrX) {
            String card = "Bus";    // Beispielwert
            int fieldToGo = 0;
            boolean drawValide = false;
            TurnMessage turnMessage;

            // Schleife wird solange ausgeführt bis ein gültiger Zug vom Spieler kommt
            while (drawValide = false) {
            /*
               Der Server holt sich vom Spieler Client die Karte die er einsetzen will
               und die Position zu der er ziehen möchte
            */
                turnMessage = lobby.askPlayerforTurn(player.getId());
                card = turnMessage.getCard();
                fieldToGo = turnMessage.getToField();
                System.out.println(card + "sssss " + fieldToGo);
            /*
               Die Daten vom Zug des Spielers werden weitergegeben an das Gameboard wo überprüft wird,
               ob der Zug gültig ist.
               Wenn der Zug nicht gültig ist wird ein neuer Zug vom Spieler abgefragt.
            */
                if (gameBoard.checkDraw(player.getId(), fieldToGo, card)) {
                    lobby.confirm(player.getId(), "yes");
                    // break;
                    drawValide = true;
                } else {
                    lobby.confirm(player.getId(), "no");

                }
                // }

        /*
            Dem Spieler muss die verwendete Karte noch aus seinen verfügbaren Karten entfernt werden
         */
                Transition toRemove = new TransitionImpl();
                toRemove.setName(card);
                player.removeTransitionFromAvailable(toRemove);

                if (player instanceof Detective) {
                    //Wenn der Detektiv den Verdacht äußert, dass Mr. X geschummelt hat.
                    if (card.equals("cheat")) {
                        if (checkIfMrXCheated()) {
                            int misterX = players.size() - 1;
                            ((MrX) (players.get(misterX))).setCaughtCheating(true, actualRound);
                            ((MrX) (players.get(misterX))).setVisibleFor(2);

                            //Detektiv darf seine Position ändern.
                            drawValide = false;
                            // Schleife wird solange ausgeführt bis ein gültiger Zug vom Spieler kommt
                            while (drawValide = false) {

                                turnMessage = lobby.askPlayerforTurn(player.getId());
                                card = turnMessage.getCard();
                                fieldToGo = turnMessage.getToField();

                                if (gameBoard.checkDraw(player.getId(), fieldToGo, card)) {
                                    lobby.confirm(player.getId(), "yes");
                                    // break;
                                    drawValide = true;
                                } else {
                                    lobby.confirm(player.getId(), "no");

                                }
                            }
                            ((Detective) player).validateTicket(card);
                            try {
                                Method getNameMethod = ((Detective) player).getClass().getMethod(getMethodName(card));
                                int count=(int) getNameMethod.invoke(((Detective) player));
                                lobby.updateTicketCount(player.getId(),count,card);
                            } catch (NoSuchMethodException nsme) {
                                nsme.printStackTrace();
                            } catch (InvocationTargetException | IllegalAccessException e ) {
                                e.printStackTrace();
                            }


                        } else {
                            //Wenn Mr. X nicht geschummelt hat, wird der Detektiv für diese und  nächste Runde als inaktiv gekennzeichnet.
                            ((Detective) player).setInactive(true);
                            fieldToGo = player.getCurrentPosition();
                        }
                    } else {
                        ((Detective) player).validateTicket(card);
                        try {
                            Method getNameMethod = ((Detective) player).getClass().getMethod(getMethodName(card));
                            int count=(int) getNameMethod.invoke(((Detective) player));
                            lobby.updateTicketCount(player.getId(),count,card);
                        } catch (NoSuchMethodException nsme) {
                            nsme.printStackTrace();
                        } catch (InvocationTargetException | IllegalAccessException e ) {
                            e.printStackTrace();
                        }
                    }
                }
                else if (player instanceof MrX) {

                    if (!card.equals("Double") && !card.equals("cheat")) {
                        //Normaler Zug von Mister X
                        ((MrX) player).validateTicket(actualRound, card, fieldToGo);
                        try {
                            Method getNameMethod = ((MrX) player).getClass().getMethod(getMethodName(card));
                            int count=(int) getNameMethod.invoke(((MrX) player));
                            lobby.updateTicketCount(player.getId(),count,card);
                        } catch (NoSuchMethodException nsme) {
                            nsme.printStackTrace();
                        } catch (InvocationTargetException | IllegalAccessException e ) {
                            e.printStackTrace();
                        }

                        if (((MrX) player).isCaughtCheating()) {
                            ((MrX) player).setCaughtCheating(true, actualRound);
                        }
                        lobby.updateTravellogToAllClients(((MrX) player).getTravelLog(actualRound), actualRound);
                    } else if (card.equals("cheat")) {

                        drawValide = false;
                        // Schleife wird solange ausgeführt bis ein gültiger Zug vom Spieler kommt
                        while (drawValide = false) {

                            turnMessage = lobby.askPlayerforTurn(player.getId());
                            card = turnMessage.getCard();
                            fieldToGo = turnMessage.getToField();


                            if (gameBoard.checkDraw(player.getId(), fieldToGo, card)) {
                                lobby.confirm(player.getId(), "yes");
                                // break;
                                drawValide = true;
                            } else {
                                lobby.confirm(player.getId(), "no");

                            }
                        }
                        //Erster Zug wird normal im Travellog gespeichert.
                        ((MrX) player).validateTicket(actualRound, "cheat", 0); //Position ist hier egal
                        ((MrX) player).validateTicket(actualRound, card, fieldToGo);
                        try {
                            Method getNameMethod = ((MrX) player).getClass().getMethod(getMethodName(card));
                            int count=(int) getNameMethod.invoke(((MrX) player));
                            lobby.updateTicketCount(player.getId(),count,card);
                        } catch (NoSuchMethodException nsme) {
                            nsme.printStackTrace();
                        } catch (InvocationTargetException | IllegalAccessException e ) {
                            e.printStackTrace();
                        }
                        if (((MrX) player).isCaughtCheating()) {
                            ((MrX) player).setCaughtCheating(true, actualRound);
                        }
                        lobby.updateTravellogToAllClients(((MrX) player).getTravelLog(actualRound), actualRound);

                        drawValide = false;
                        // Schleife wird solange ausgeführt bis ein gültiger Zug vom Spieler kommt
                        while (drawValide = false) {

                            turnMessage = lobby.askPlayerforTurn(player.getId());
                            card = turnMessage.getCard();
                            fieldToGo = turnMessage.getToField();


                            if (gameBoard.checkDraw(player.getId(), fieldToGo, card)) {
                                lobby.confirm(player.getId(), "yes");
                                // break;
                                drawValide = true;
                            } else {
                                lobby.confirm(player.getId(), "no");

                            }
                        }
                        try {
                            Method getNameMethod = ((MrX) player).getClass().getMethod(getMethodName(card));
                            int count=(int) getNameMethod.invoke(((MrX) player));
                            lobby.updateTicketCount(player.getId(),count,card);
                        } catch (NoSuchMethodException nsme) {
                            nsme.printStackTrace();
                        } catch (InvocationTargetException | IllegalAccessException e ) {
                            e.printStackTrace();
                        }

                        //Zweiter, geschummelter Zug wird nicht im Travellog gespeichert und ist somit nicht sichtbar für Detektive.
                        player.setCurrentPosition(fieldToGo);

                    } else {
                        /**
                         *      Warten bis Spieler den ersten Zug des Doppelzugs macht.
                         */
                        drawValide = false;
                        // Schleife wird solange ausgeführt bis ein gültiger Zug vom Spieler kommt
                        while (drawValide = false) {

                            turnMessage = lobby.askPlayerforTurn(player.getId());
                            card = turnMessage.getCard();
                            fieldToGo = turnMessage.getToField();

                            if (gameBoard.checkDraw(player.getId(), fieldToGo, card)) {
                                lobby.confirm(player.getId(), "yes");
                                // break;
                                drawValide = true;
                            } else {
                                lobby.confirm(player.getId(), "no");

                            }
                        }
                        //Erster Zug wird im Travellog normal gespeichert.
                        ((MrX) player).validateDoubleMoveTicket(actualRound, card, fieldToGo);
                        if (((MrX) player).isCaughtCheating()) {
                            ((MrX) player).setCaughtCheating(false, actualRound);
                        }
                        try {
                            Method getNameMethod = ((MrX) player).getClass().getMethod(getMethodName(card));
                            int count=(int) getNameMethod.invoke(((MrX) player));
                            lobby.updateTicketCount(player.getId(),count,card);
                        } catch (NoSuchMethodException nsme) {
                            nsme.printStackTrace();
                        } catch (InvocationTargetException | IllegalAccessException e ) {
                            e.printStackTrace();
                        }
                        lobby.updateTravellogToAllClients(((MrX) player).getTravelLog(actualRound), actualRound);

                        /**
                         *      Warten auf den zweiten Zug des Doppelzugs.
                         */
                        drawValide = false;
                        // Schleife wird solange ausgeführt bis ein gültiger Zug vom Spieler kommt
                        while (drawValide = false) {

                            turnMessage = lobby.askPlayerforTurn(player.getId());
                            card = turnMessage.getCard();
                            fieldToGo = turnMessage.getToField();

                            if (gameBoard.checkDraw(player.getId(), fieldToGo, card)) {
                                lobby.confirm(player.getId(), "yes");
                                // break;
                                drawValide = true;
                            } else {
                                lobby.confirm(player.getId(), "no");

                            }
                        }
                        //Zweiter Zug wird im Travellog normal gespeichert.
                        ((MrX) player).validateTicket(actualRound, card, fieldToGo);
                        if (((MrX) player).isCaughtCheating()) {
                            ((MrX) player).setCaughtCheating(false, actualRound);
                        }
                        try {
                            Method getNameMethod = ((MrX) player).getClass().getMethod(getMethodName(card));
                            int count=(int) getNameMethod.invoke(((MrX) player));
                            lobby.updateTicketCount(player.getId(),count,card);
                        } catch (NoSuchMethodException nsme) {
                            nsme.printStackTrace();
                        } catch (InvocationTargetException | IllegalAccessException e ) {
                            e.printStackTrace();
                        }
                        lobby.updateTravellogToAllClients(((MrX) player).getTravelLog(actualRound), actualRound);
                    }
                /*
                    Wenn Mister X beim Schummeln erwischt wurde, wird hier heruntergezählt,
                    wie lange seine Position noch für die Detektive sichtbar ist.
                 */
                    if (((MrX) player).getVisibleFor() == 0) {
                        ((MrX) player).setCaughtCheating(false, actualRound);
                    }
                    if (((MrX) player).isCaughtCheating()) {
                        ((MrX) player).setVisibleFor(((MrX) player).getVisibleFor() - 1);
                    }

                }
                player.setCurrentPosition(fieldToGo);

                /*
                    Wenn der Zug gültig ist, wird die Positon des Spielers auf dem Gameboard gesetzt
                    und an die anderen Spieler Clients weitergegeben
                 */
                if (drawValide) {
                    gameBoard.setPositionOfPlayer(player.getId(), fieldToGo);
                    lobby.updatePlayerPositionsToAllClients(player.getId(), fieldToGo);
                } else {
                    ((Detective) player).setInactive(false);
                }
            }
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



    public void initLobby (Lobby lobby){
        this.lobby = lobby;
    }

    public void setLobby (Lobby lobby){
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

    public String getMethodName(String card){
        if (card.equals("U-Bahn")){
            return "getUndergroundTickets";
        }
        return "get"+card+"Tickets";
    }
}


