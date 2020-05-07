package com.example.scotlandyard.modelLayer.players.implementation;


import com.example.scotlandyard.modelLayer.players.TravelLog;
import com.example.scotlandyard.modelLayer.players.interfaces.MrX;

public class MrXImpl extends PlayerImpl implements MrX {
    private int blackTickets;
    private int doubleMoveTickets;
    /**
     * @see #setPositionOfRound(int, int, int, boolean)
     */
    private TravelLog[] travelLog =new TravelLog[24];


    public MrXImpl(String name, int id){
        super(name, id);
        blackTickets=5;
        doubleMoveTickets=2;
    }

    /**
     * Löst ein Ticket ein.
     * @param round Spielrunde (Runde 1-24)
     * @param ticketType Ticket, welches zu gegebener Runde eingelöst wurde (1=Taxi, 2=Bus, 3=U-Bahn, 4=Black Ticket, 5=Double Move)
     * @param newPosition Position
     */
    @Override
    public void validateTicket(int round, int ticketType, int newPosition){
        if (ticketType==4){
            if(blackTickets>0){
                blackTickets--;
                setPositionOfRound(newPosition,4,round,false);
            }else {
                throw new IllegalArgumentException("No Black Tickets left.");
            }
        }else if (ticketType==5){
            if(doubleMoveTickets>0){
                doubleMoveTickets--;
                setPositionOfRound(newPosition, ticketType, round, true);
            }else {
                throw new IllegalArgumentException("No Double Move Tickets left.");
            }
        }else {
           setPositionOfRound(newPosition, ticketType, round, false);
        }
    }

    /**
     * Löst ein Double Move Ticket ein und speichert den ERSTEN Zug des Doppelzugs.
     *Zweiter Zug wird normal mit {@link #validateTicket(int, int, int)} Methode im Travel Log gespeichert.
     * @param round Spielrunde (Runde 1-24)
     * @param ticketType Ticket, welches zu gegebener Runde eingelöst wurde (1=Taxi, 2=Bus, 3=U-Bahn, 4=Black Ticket)
     * @param newPosition Position
     */
    public void validateDoubleMoveTicket(int round, int ticketType, int newPosition){
        if(doubleMoveTickets>0){
            doubleMoveTickets--;
            setPositionOfRound(newPosition, ticketType, round, true);
        }else {
            throw new IllegalArgumentException("No Double Move Tickets left.");
        }
    }

    /**
     * Speichert die Position und das eingelöste Ticket zu einer Spielrunde.
     * @param position Position von Mr.X zu gegebener Runde
     * @param ticket Ticket, welches zu gegebener Runde eingelöst wurde (1=Taxi, 2=Bus, 3=U-Bahn, 4=Black Ticket, 5=Double Move)
     * @param round Spielzugrunde (Runde 1-24)
     * @param isDoubleMove true= wenn in dieser Runde ein Doppelzug-Ticket gelöst wurde.
     */
    @Override
    public void setPositionOfRound(int position, int ticket, int round, boolean isDoubleMove) {
        TravelLog log=new TravelLog(position, ticket, isDoubleMove);
        travelLog[round-1]=log;
    }

    /**
     * Methode zum Ermitteln der Position von Mr.X zu einer gewissen Spielrunde.
     * @param round Werte von 1 bis 24
     * @return Spielfeldnummer auf der sich Mr.X befand/befindet.
     */
    @Override
    public int getPositionOfRound(int round) {
        return travelLog[round-1].getPosition();
    }

    /**
     * Methode zum Ermitteln mit welchem Verkehrsmittel Mr.X sich zu einer gewissen Spielrunde bewegt hat.
     * @param round Werte von 1 bis 24
     * @return eingelöstes Ticket (1=Taxi, 2=Bus, 3=U-Bahn, 4=Black Ticket)
     */
    @Override
    public int getTicketOfRound(int round){
        return travelLog[round-1].getTicket();
    }

    /**
     * Methode zum Ermitteln, ob Mr.X ein Double Move Ticket eingelöst hat zu einer gewissen Spielrunde.
     * @param round Werte 1-24
     * @return true=Double Move Ticket wurde benutzt.
     */
    public boolean isDoubleMoveAtRound(int round){
        return travelLog[round-1].isDoubleMove();
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
        blackTickets=ticketNumber;
    }

    @Override
    public void setDoubleMoveTickets(int ticketNumber) {
        doubleMoveTickets=ticketNumber;
    }



}
