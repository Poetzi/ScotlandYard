package com.example.scotlandyard.modelLayer.gameBoard.interfaces;

import com.example.scotlandyard.modelLayer.transitions.interfaces.Transition;

public interface GameBoard {

    void addFieldWithTransition(int fromField, int toField, Transition Rule);

    void addField(int field);

    void removeField(int field);

    boolean movePlayer(int fromField, int toField);

    void setStartField(int playerId, int field);

    int getPositionOffPlayer(int playerId);

}
