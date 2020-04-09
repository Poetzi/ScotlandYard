package com.example.scotlandyard.modelLayer.gameBoard.implementation;

import com.example.scotlandyard.modelLayer.gameBoard.interfaces.GameBoard;
import com.example.scotlandyard.modelLayer.transitions.interfaces.Transition;

public class GameBoardImpl implements GameBoard {
    @Override
    public void addFieldWithTransition(int fromField, int toField, Transition Rule) {

    }

    @Override
    public void addField(int field) {

    }

    @Override
    public void removeField(int field) {

    }

    @Override
    public boolean movePlayer(int fromField, int toField) {
        return false;
    }

    @Override
    public void setStartField(int playerId, int field) {

    }

    @Override
    public int getPositionOffPlayer(int playerId) {
        return 0;
    }
}
