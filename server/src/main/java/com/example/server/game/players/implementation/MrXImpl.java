package com.example.server.game.players.implementation;


import com.example.server.game.players.TravelLog;
import com.example.server.game.players.interfaces.MrX;

public class MrXImpl extends PlayerImpl implements MrX {
    private int blackTickets;
    private int doubleMoveTickets;
    private int cheatTickets;
    private boolean caughtCheating;
    private int visibleFor;
    /**
     * @see #setPositionOfRound(int, String, int, boolean)
     */
    private TravelLog[] travelLog = new TravelLog[24];


    public MrXImpl(String name, int id) {
        super(name, id);
        blackTickets = 5;
        doubleMoveTickets = 2;
        cheatTickets = 5;
        caughtCheating = false;
        visibleFor = 0;
    }

    @Override
    public TravelLog getTravelLog(int round) {
        return travelLog[round - 1];
    }

    @Override
    public void setTravelLog(TravelLog travelLog, int round) {
        this.travelLog[round - 1] = travelLog;
    }

    /**
     * Löst ein Ticket ein.
     *
     * @param round       Spielrunde (Runde 1-24)
     * @param ticketType  Ticket, welches zu gegebener Runde eingelöst wurde
     * @param newPosition Position
     */
    @Override
    public void validateTicket(int round, String ticketType, int newPosition) {
        if (ticketType.equals("Black")) {
            if (blackTickets > 0) {
                blackTickets--;
                setPositionOfRound(newPosition, "Black", round, false);
            } else {
                throw new IllegalArgumentException("No Black Tickets left.");
            }
        } else if (ticketType.equals("cheat")) {
            if (cheatTickets > 0) {
                cheatTickets--;
                setHasCheated(round);
            } else {
                throw new IllegalArgumentException("No cheat tickets left.");
            }
        } else {
            setPositionOfRound(newPosition, ticketType, round, false);
        }
    }

    /**
     * Löst ein Double Move Ticket ein und speichert den ERSTEN Zug des Doppelzugs.
     * Zweiter Zug wird normal mit {@link #validateTicket(int, String, int)} Methode im Travel Log gespeichert.
     *
     * @param round       Spielrunde (Runde 1-24)
     * @param ticketType  Ticket, welches zu gegebener Runde eingelöst wurde
     * @param newPosition Position
     */
    public void validateDoubleMoveTicket(int round, String ticketType, int newPosition) {
        if (doubleMoveTickets > 0) {
            doubleMoveTickets--;
            setPositionOfRound(newPosition, ticketType, round, true);
        } else {
            throw new IllegalArgumentException("No Double Move Tickets left.");
        }
    }

    /**
     * Wenn Mr. X schummelt wird Variable hasCheated in {@link TravelLog} true gesetzt.
     *
     * @param round Zu welcher Runde geschummelt wurde.
     */
    public void setHasCheated(int round) {
        travelLog[round - 1].setHasCheated(true);
    }

    /**
     * Liefert Boolean Wert ob zur gegebenen Runde geschummelt wurde.
     *
     * @param round Integerwerte von 1 bis 24.
     * @return Boolean Wert (true = Mr. X hat in dieser Runde geschummelt).
     */
    public boolean getHasCheatedInRound(int round) {
        return travelLog[round - 1].isHasCheated();
    }

    /**
     * Speichert die Position und das eingelöste Ticket zu einer Spielrunde.
     *
     * @param position     Position von Mr.X zu gegebener Runde
     * @param ticket       Ticket, welches zu gegebener Runde eingelöst wurde (1=Taxi, 2=Bus, 3=U-Bahn, 4=Black Ticket, 5=Double Move)
     * @param round        Spielzugrunde (Runde 1-24)
     * @param isDoubleMove true= wenn in dieser Runde ein Doppelzug-Ticket gelöst wurde.
     */
    @Override
    public void setPositionOfRound(int position, String ticket, int round, boolean isDoubleMove) {
        TravelLog log = new TravelLog(position, ticket, isDoubleMove);
        travelLog[round - 1] = log;
    }

    /**
     * Methode zum Ermitteln der Position von Mr.X zu einer gewissen Spielrunde.
     *
     * @param round Werte von 1 bis 24
     * @return Spielfeldnummer auf der sich Mr.X befand/befindet.
     */
    @Override
    public int getPositionOfRound(int round) {
        return travelLog[round - 1].getPosition();
    }

    /**
     * Methode zum Ermitteln mit welchem Verkehrsmittel Mr.X sich zu einer gewissen Spielrunde bewegt hat.
     *
     * @param round Werte von 1 bis 24
     * @return eingelöstes Ticket (1=Taxi, 2=Bus, 3=U-Bahn, 4=Black Ticket)
     */
    @Override
    public String getTicketOfRound(int round) {
        return travelLog[round - 1].getTicket();
    }

    /**
     * Methode zum Ermitteln, ob Mr.X ein Double Move Ticket eingelöst hat zu einer gewissen Spielrunde.
     *
     * @param round Werte 1-24
     * @return true=Double Move Ticket wurde benutzt.
     */
    @Override
    public boolean isDoubleMoveAtRound(int round) {
        return travelLog[round - 1].isDoubleMove();
    }

    @Override
    public int getBlackTickets() {
        return blackTickets;
    }

    @Override
    public int getDoubleMoveTickets() {
        return doubleMoveTickets;
    }


    @Override
    public void setBlackTickets(int ticketNumber) {
        blackTickets = ticketNumber;
    }

    @Override
    public void setDoubleMoveTickets(int ticketNumber) {
        doubleMoveTickets = ticketNumber;
    }

    public int getCheatTickets() {
        return cheatTickets;
    }

    public void setCheatTickets(int cheatTickets) {
        this.cheatTickets = cheatTickets;
    }

    public boolean isCaughtCheating() {
        return caughtCheating;
    }

    public void setCaughtCheating(boolean caughtCheating, int round) {
        this.caughtCheating = caughtCheating;
        travelLog[round - 1].setCaughtCheating(caughtCheating);
    }

    public int getVisibleFor() {
        return visibleFor;
    }

    public void setVisibleFor(int visibleFor) {
        this.visibleFor = visibleFor;
    }

    public TravelLog[] getTravelLog() {
        return travelLog;
    }

    public void setTravelLog(TravelLog[] travelLog) {
        this.travelLog = travelLog;
    }
}

