package com.example.scotlandyard.modelLayer.players.interfaces;

public interface Detective extends Player {

    int getTaxiTickets();

    int getBusTickets();

    int getUndergroundTickets();

    void setTaxiTickets(int ticketNumber);

    void setBusTickets(int ticketNumber);

    void setUndergoundTickets(int ticketNumber);

}
