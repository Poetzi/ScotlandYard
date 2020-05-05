package com.example.scotlandyard.modelLayer.players;

public class TravelLog {

    private int[] move; //move[0]=Position, move[1]=Ticket Typ
    private boolean isDoubleMove;

    public TravelLog(int position, int ticket, boolean isDoubleMove){
        move=new int [] {position, ticket};
        this.isDoubleMove=isDoubleMove;
    }

    public int[] getMove() {
        return move;
    }

    public void setMove(int[] move) {
        this.move = move;
    }

    public boolean isDoubleMove() {
        return isDoubleMove;
    }

    public void setDoubleMove(boolean doubleMove) {
        this.isDoubleMove = doubleMove;
    }

    public int getPosition(){
        return move[0];
    }

    public int getTicket(){
        return move[1];
    }
}