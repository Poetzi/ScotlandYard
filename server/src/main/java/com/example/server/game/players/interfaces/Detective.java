package com.example.server.game.players.interfaces;

public interface Detective extends Player {

    void validateTicket(String ticketType);

    int getTaxiTickets();

    int getBusTickets();

    int getUndergroundTickets();

    void setTaxiTickets(int ticketNumber);

    void setBusTickets(int ticketNumber);

    void setUndergoundTickets(int ticketNumber);

    boolean isInactive();

    void setInactive(boolean inactive);

}
