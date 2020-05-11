package com.example.scotlandyard.modelLayer.players.interfaces;

public interface MrX extends Player {
    int getBlackTickets();

    int getDoubleMoveTickets();

    int getPositionOfRound(int round);

    String getTicketOfRound(int round);

    boolean isDoubleMoveAtRound(int round);

    void setHasCheated(int round, int newPosition);

    boolean getHasCheatedInRound(int round);

    void setBlackTickets(int ticketNumber);

    void setDoubleMoveTickets(int ticketNumber);

    void setPositionOfRound(int position, String ticket, int round, boolean isDoubleMove);

    void validateTicket(int round, String ticketType, int newPosition);

    void validateDoubleMoveTicket(int round, String ticketType, int newPosition);
}
