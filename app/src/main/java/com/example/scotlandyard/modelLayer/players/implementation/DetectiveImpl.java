
package com.example.scotlandyard.modelLayer.players.implementation;

import com.example.scotlandyard.modelLayer.players.interfaces.Detective;

import java.util.ArrayList;

public class DetectiveImpl extends PlayerImpl implements Detective {


    public DetectiveImpl(String name, int id) {
        super(name, id);
    }


    public void validateTicket(String ticketType){

    }

    @Override
    public int getTaxiTickets() {
        return -1;
    }

    @Override
    public int getBusTickets() {
        return -1;
    }

    @Override
    public int getUndergroundTickets() {
        return -1;
    }

    @Override
    public void setTaxiTickets(int ticketNumber) {

    }

    @Override
    public void setBusTickets(int ticketNumber) {

    }


    @Override
    public void setUndergoundTickets(int ticketNumber) {

    }
}
