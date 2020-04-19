package com.example.scotlandyard.modelLayer.gameBoard.implementation;

import com.example.scotlandyard.modelLayer.gameBoard.interfaces.GameBoard;
import com.example.scotlandyard.modelLayer.transitions.interfaces.Transition;

import java.util.HashSet;

public class GameBoardImpl implements GameBoard {

    /*
       Jede Stelle im Arrray steht für einen Spieler
     */
    int [] playersPositions;
    /*
        Speichert die Anzahl der Spielfelder
     */
    HashSet<Integer> fields; //(Liste oder Set von Field)
    /*
        Speichert die Wege von einem Spielfeld zum anderen
     */
    HashSet<Transition> transitions; //(Liste oder Set)


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
