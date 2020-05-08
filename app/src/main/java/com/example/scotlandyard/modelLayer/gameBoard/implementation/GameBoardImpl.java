package com.example.scotlandyard.modelLayer.gameBoard.implementation;

import com.example.scotlandyard.modelLayer.gameBoard.interfaces.GameBoard;
import com.example.scotlandyard.modelLayer.transitions.implementation.TransitionImpl;
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
        Transition newTransition = new TransitionImpl(Rule.getName(),toField,fromField);

        transitions.add(newTransition);
    }

    @Override
    public void addField(int field) {
        fields.add(field);
    }

    @Override
    public void removeField(int field) {
        fields.remove(field);
    }

    @Override
    public boolean movePlayer(int fromField, int toField, Transition Rule) {
        Transition check = new TransitionImpl(Rule.getName(),toField,fromField);

        return transitions.contains(check);
    }

    @Override
    public void setStartField(int playerId, int field) {
        playersPositions[playerId] = field;
    }

    @Override
    public int getPositionOffPlayer(int playerId) {
        return playersPositions[playerId];
    }
}
