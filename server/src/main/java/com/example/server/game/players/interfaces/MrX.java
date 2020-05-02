package com.example.server.game.players.interfaces;

public interface MrX extends Player {
    int getBlackTickets();

    int getDoubleMoveTickets();

    int getPositionOfRound(int round);

    int getTicketOfRound(int round);

    void setBlackTickets(int ticketNumber);

    void setDoubleMoveTickets(int ticketNumber);

    void setPositionOfRound(int position, int ticket, int round);

    void validateBlackTicket();

    void validateDoubleMove();

}
