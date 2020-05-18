package com.example.scotlandyard.modelLayer.transitions.implementation;

import com.example.scotlandyard.modelLayer.transitions.interfaces.Transition;

public class TransitionImpl implements Transition {

    private String name;
    private int toField;
    private int fromField;

    public TransitionImpl(String name, int toField, int fromField) {
        this.name = name;
        this.toField = toField;
        this.fromField = fromField;
    }

    public TransitionImpl() {
    }


    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setFromField(int fromField) {
        this.fromField = fromField;
    }

    @Override
    public void setToField(int toField) {
        this.toField = toField;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getFromField() {
        return this.fromField;
    }

    @Override
    public int getToField() {
        return this.toField;
    }
}
