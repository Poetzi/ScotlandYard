package com.example.server.game.players.implementation;


import com.example.server.game.players.interfaces.Player;
import com.example.server.game.transitions.interfaces.Transition;

import java.util.ArrayList;

public class PlayerImpl implements Player {

    private String name;
    private int id;
    private int currentPosition;
    private ArrayList<Transition> availableTransitions = new ArrayList<>();

    public PlayerImpl() {
    }

    public PlayerImpl(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setAvailableTransitions(ArrayList<Transition> transitions) {
        this.availableTransitions = transitions;
    }

    @Override
    public void removeTransitionFromAvailable(Transition transition) {
        if (this.availableTransitions.contains(transition)) {
            this.availableTransitions.remove(transition);
        }
    }

    @Override
    public int getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public void setCurrentPosition(int field) {
        currentPosition = field;
    }

    public ArrayList<Transition> getAvailableTransitions() {
        return availableTransitions;
    }

    @Override
    public int getTaxiTickets() {
        return 24;
    }

    @Override
    public int getBusTickets() {
        return 24;
    }

    @Override
    public int getUndergroundTickets() {
        return 24;
    }

    @Override
    public int getDoubleMoveTickets() {
        return 0;
    }

    @Override
    public int getBlackTickets() {
        return 0;
    }

    public int getCheatTickets(){return 24;}
}
