
package com.example.scotlandyard.modelLayer.players.implementation;

import com.example.scotlandyard.modelLayer.players.interfaces.Detective;
import com.example.scotlandyard.modelLayer.transitions.implementation.TransitionImpl;
import com.example.scotlandyard.modelLayer.transitions.interfaces.Transition;

import java.util.ArrayList;

    public class DetectiveImpl extends PlayerImpl implements Detective {
        private int taxiTickets;
        private int busTickets;
        private int undergroundTickets;
        private ArrayList<Transition> availableTransitions;


    public DetectiveImpl(String name, int id){
        super(name, id);
        availableTransitions=getAvailableTransitions();
        taxiTickets=11;
        busTickets=8;
        undergroundTickets=4;
        //Beispielswerte
        Transition x=new TransitionImpl();
        x.setFromField(1);
        x.setToField(2);
        x.setName("Taxi");
        for (int i = 0; i < taxiTickets; i++) {
            availableTransitions.add(x);
        }
        x=new TransitionImpl();
        x.setFromField(1);
        x.setToField(2);
        x.setName("Bus");
        for (int i = 0; i < busTickets; i++) {
            availableTransitions.add(x);
        }
        x=new TransitionImpl();
        x.setFromField(1);
        x.setToField(2);
        x.setName("U-Bahn");
        for (int i = 0; i < undergroundTickets; i++) {
            availableTransitions.add(x);
        }
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
