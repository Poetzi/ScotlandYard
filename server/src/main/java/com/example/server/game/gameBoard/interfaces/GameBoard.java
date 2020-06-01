package com.example.server.game.gameBoard.interfaces;


import com.example.server.game.transitions.interfaces.Transition;

public interface GameBoard {

    void addFieldWithTransition(int fromField, int toField, String Rule);

    void addField(int field);

    void removeField(int field);

    boolean checkDraw(int playerId, int toField, String rule);

    void setStartField(int playerId, int field);

    void setPositionOfPlayer(int playerID, int toField);

    int getPositionOfPlayer(int playerId);

}
