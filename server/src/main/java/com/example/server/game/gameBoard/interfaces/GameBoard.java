package com.example.server.game.gameBoard.interfaces;


import com.example.server.game.transitions.interfaces.Transition;

public interface GameBoard {

    void addFieldWithTransition(int fromField, int toField, Transition Rule);

    void addField(int field);

    void removeField(int field);

    boolean movePlayer(int fromField, int toField, Transition Rule);

    void setStartField(int playerId, int field);

    int getPositionOffPlayer(int playerId);

}
