package com.example.server.game.players.interfaces;

public interface Detective extends Player {

    void validateTicket(int ticketType);

    int getTaxiTickets();

    int getBusTickets();

    int getUndergroundTickets();

    void setTaxiTickets(int ticketNumber);

    void setBusTickets(int ticketNumber);

    void setUndergoundTickets(int ticketNumber);

}
