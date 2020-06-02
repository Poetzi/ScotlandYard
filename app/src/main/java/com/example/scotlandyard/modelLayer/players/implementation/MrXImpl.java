package com.example.scotlandyard.modelLayer.players.implementation;



import com.example.scotlandyard.modelLayer.players.interfaces.MrX;

public class MrXImpl extends PlayerImpl implements MrX {




    public MrXImpl(String name, int id){
        super(name, id);

    }


    @Override
    public void validateTicket(int round, String ticketType, int newPosition){

    }


    public void validateDoubleMoveTicket(int round, String ticketType, int newPosition){

    }


    public void setHasCheated(int round, int newPosition){

    }


    public boolean getHasCheatedInRound(int round){
        return false;
    }


    @Override
    public void setPositionOfRound(int position, String ticket, int round, boolean isDoubleMove) {

    }


    @Override
    public int getPositionOfRound(int round) {
        return -1;
    }


    @Override
    public String getTicketOfRound(int round){
        return null;
    }


    public boolean isDoubleMoveAtRound(int round){
        return false;
    }

    @Override
    public int getBlackTickets() {
        return -1;
    }

    @Override
    public int getDoubleMoveTickets() {
        return -1;
    }


    @Override
    public void setBlackTickets(int ticketNumber) {

    }

    @Override
    public void setDoubleMoveTickets(int ticketNumber) {

    }



}
