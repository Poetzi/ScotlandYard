package com.example.scotlandyard.modelLayer.players.implementation;

import com.example.scotlandyard.modelLayer.players.interfaces.Detective;

public class DetectiveImpl extends PlayerImpl implements Detective {
    private int taxiTickets;
    private int busTickets;
    private int undergroundTickets;

    public DetectiveImpl(){
        taxiTickets=11;
        busTickets=8;
        undergroundTickets=4;
    }

    public DetectiveImpl(String name, int id){
        super(name, id);
        taxiTickets=11;
        busTickets=8;
        undergroundTickets=4;
    }

    @Override
    public int getTaxiTickets() {
        return taxiTickets;
    }

    @Override
    public int getBusTickets() {
        return busTickets;
    }

    @Override
    public int getUndergroundTickets() {
        return undergroundTickets;
    }

    @Override
    public void setTaxiTickets(int ticketNumber) {
        taxiTickets=ticketNumber;
    }

    @Override
    public void setBusTickets(int ticketNumber) {
        busTickets=ticketNumber;
    }

    @Override
    public void setUndergoundTickets(int ticketNumber) {
        undergroundTickets=ticketNumber;
    }
}
