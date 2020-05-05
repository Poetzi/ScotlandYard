
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

        /**
         * Löst ein Ticket ein.
          * @param ticketType gibt an welches Ticket eingelöst werden soll (1=Taxi, 2=Bus, 3=U-Bahn, 4=Black Ticket, 5=Double Move).
         */
    public void validateTicket(int ticketType){
        if (ticketType==1){
            if (taxiTickets>0){
                taxiTickets--;
            }else {
                throw new IllegalArgumentException("No Taxi Tickets left.");
            }
        }else if (ticketType==2){
            if (busTickets>0){
                busTickets--;
            }else {
                throw new IllegalArgumentException("No Bus Tickets left.");
            }
        }else if(ticketType==3){
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
}
