package com.example.scotlandyard.modelLayer.players.interfaces;

import com.example.scotlandyard.modelLayer.transitions.interfaces.Transition;

import java.util.ArrayList;

public interface Player {
    String getName();

    void setName(String name);

    void setId(int id);

    int getId();

    void setAvailableTransitions(ArrayList<Transition> transitions);

    void removeTransitionFromAvailable(Transition transition);

    int getCurrentPosition();

    void setCurrentPosition(int field);

    ArrayList<Transition> getAvailableTransitions();
}
