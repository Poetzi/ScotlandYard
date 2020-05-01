package com.example.server.game.transitions.interfaces;

public interface Transition {
    void setName(String name);

    void setFromField(int fromField);

    void setToField(int toField);

    String getName();

    int getFromField();

    int getToField();
}
