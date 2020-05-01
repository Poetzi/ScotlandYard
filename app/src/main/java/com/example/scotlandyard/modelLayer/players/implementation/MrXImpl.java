package com.example.scotlandyard.modelLayer.players.implementation;

import com.example.scotlandyard.modelLayer.players.interfaces.MrX;

public class MrXImpl extends PlayerImpl implements MrX {
    private int blackTickets;
    private int doubleMoveTickets;
    /**
     * @see #setPositionOfRound(int, int, int) 
     */
    private int [][] travelLog =new int[24][2];


    public MrXImpl(String name, int id){
        super(name, id);
        blackTickets=5;
        doubleMoveTickets=2;
    }

    @Override
    public void validateBlackTicket() {

    }

    @Override
    public void validateDoubleMove() {

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
    public int getPositionOfRound(int round) {
        return travelLog[round-1][0];
    }

    @Override
    public int getTicketOfRound(int round){
        return travelLog[round-1][1];
    }

    @Override
    public void setBlackTickets(int ticketNumber) {
        blackTickets=ticketNumber;
    }

    @Override
    public void setDoubleMoveTickets(int ticketNumber) {
        doubleMoveTickets=ticketNumber;
    }

    /**
     * Speichert die Position und das eingelöste Ticket zu einer Spielrunde.
     * @param position Position von Mr.X zu gegebener Runde
     * @param ticket Ticket, welches zu gegebener Runde eingelöst wurde (1=Taxi, 2=Bus, 3=U-Bahn, 4=Black Ticket)
     * @param round Spielzugrunde (Runde 1-24)
     */
    @Override
    public void setPositionOfRound(int position, int ticket, int round) {
        travelLog[round-1][0]=position;
        travelLog[round-1][1]=ticket;
    }


}
