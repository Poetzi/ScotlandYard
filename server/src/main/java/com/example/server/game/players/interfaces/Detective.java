package com.example.server.game.players.interfaces;

public interface Detective extends Player {

    void validateTicket(String ticketType);

    void setTaxiTickets(int ticketNumber);

    void setBusTickets(int ticketNumber);

    void setUndergoundTickets(int ticketNumber);

    boolean isInactive();

    void setInactive(boolean inactive);

}
