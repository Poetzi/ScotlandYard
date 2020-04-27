package com.example.server.game.gameBoard.interfaces;


import com.example.server.game.transitions.interfaces.Transition;

public interface GameBoard {

    void addFieldWithTransition(int fromField, int toField, Transition Rule);

    void addField(int field);

    void removeField(int field);

    boolean checkDraw(int playerID, int toField, String card);

    void setStartField(int playerId, int field);

    int getPositionOffPlayer(int playerId);

}
