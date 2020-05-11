package com.example.scotlandyard.modelLayer.gameBoard.implementation;

import android.util.Log;

import com.example.scotlandyard.modelLayer.gameBoard.interfaces.GameBoard;
import com.example.scotlandyard.modelLayer.transitions.implementation.TransitionImpl;
import com.example.scotlandyard.modelLayer.transitions.interfaces.Transition;

import java.util.ArrayList;
import java.util.HashSet;

public class GameBoardImpl implements GameBoard {

    /*
       Jede Stelle im Arrray steht für einen Spieler
     */
    int [] playersPositions;
    /*
        Speichert die Anzahl der Spielfelder
     */
    HashSet<Integer> fields = new HashSet<>(); //(Liste oder Set von Field)

    /*
            Speichert die Wege von einem Spielfeld zum anderen
         */
    ArrayList<Transition> transitions = new ArrayList<>(); //(Liste oder Set)


    @Override
    public void addFieldWithTransition(int fromField, int toField, String Rule) {
        Transition newTransition = new TransitionImpl(Rule,toField,fromField);

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
    public boolean movePlayer(int fromField, int toField, String Rule) {
        for (int i = 0; i <transitions.size() ; i++) {
            if(transitions.get(i).getToField()==toField && transitions.get(i).getFromField() == fromField && transitions.get(i).getName().equalsIgnoreCase(Rule)){
                return true;
            }
        }
        return false;
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
