package com.example.server.game.players.implementation;


import com.example.server.game.players.interfaces.Detective;
import com.example.server.game.transitions.implementation.TransitionImpl;
import com.example.server.game.transitions.interfaces.Transition;

import java.util.ArrayList;

public class DetectiveImpl extends PlayerImpl implements Detective {
    private int taxiTickets;
    private int busTickets;
    private int undergroundTickets;
    private ArrayList<Transition> availableTransitions;

    private boolean inactive;

    public DetectiveImpl(String name, int id){
        super(name, id);
        availableTransitions=getAvailableTransitions();
        taxiTickets=11;
        busTickets=8;
        undergroundTickets=4;
        inactive=false;
        //Beispielswerte
        for (int i = 0; i <taxiTickets; i++) {
            availableTransitions.add(new TransitionImpl("Taxi",1,2));
        }

        for (int i = 0; i <busTickets; i++) {
            availableTransitions.add(new TransitionImpl("Bus",1,2));
        }

        for (int i = 0; i <undergroundTickets; i++) {
            availableTransitions.add(new TransitionImpl("U-Bahn",1,2));
        }
    }

    /**
     * Löst ein Ticket ein.
     * @param ticketType gibt an welches Ticket eingelöst werden soll
     */
    public void validateTicket(String ticketType){
        if (ticketType.equals("Taxi")){
            if (taxiTickets>0){
                taxiTickets--;
            }else {
                throw new IllegalArgumentException("No Taxi Tickets left.");
            }
        }else if (ticketType.equals("Bus")){
            if (busTickets>0){
                busTickets--;
            }else {
                throw new IllegalArgumentException("No Bus Tickets left.");
            }
        }else if(ticketType.equals("U-Bahn")){
            if (undergroundTickets>0){
                undergroundTickets--;
            }else {
                throw new IllegalArgumentException("No Metro Tickets left.");
            }
        }else {
            throw new IllegalArgumentException("Ticket type "+ticketType+" not allowed here.");
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

    @Override
    public boolean isInactive() {
        return inactive;
    }

    @Override
    public void setInactive(boolean inactive) {
        this.inactive=inactive;
    }
}
