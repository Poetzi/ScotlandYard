package com.example.scotlandyard.modelLayer.transitions.implementation;

import com.example.scotlandyard.modelLayer.transitions.interfaces.Transition;

public class TransitionImpl implements Transition {

    private String name;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
