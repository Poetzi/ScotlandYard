package com.example.scotlandyard.modelLayer.players;

public class TravelLog {

    private boolean hasCheated;
    private int position;
    private String ticketType;
    private boolean isDoubleMove;

    public TravelLog(int position, String ticket, boolean isDoubleMove){
        this.position=position;
        this.ticketType=ticket;
        this.isDoubleMove=isDoubleMove;
    }


    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public void setPosition(int position){
        this.position=position;
    }

    public boolean isDoubleMove() {
        return isDoubleMove;
    }

    public void setDoubleMove(boolean doubleMove) {
        this.isDoubleMove = doubleMove;
    }

    public int getPosition(){
        return position;
    }

    public String getTicket(){
        return ticketType;
    }

    public boolean isHasCheated() {
        return hasCheated;
    }

    public void setHasCheated(boolean hasCheated) {
        this.hasCheated = hasCheated;
    }
}
