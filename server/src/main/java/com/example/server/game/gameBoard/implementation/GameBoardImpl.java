package com.example.server.game.gameBoard.implementation;



import com.example.server.game.gameBoard.interfaces.GameBoard;
import com.example.server.game.transitions.implementation.TransitionImpl;
import com.example.server.game.transitions.interfaces.Transition;

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
        Transition newTransition = new TransitionImpl();
        newTransition.setFromField(fromField);
        newTransition.setToField(toField);
        newTransition.setName(Rule.getName());

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
    public boolean checkDraw(int playerID, int toField, String card) {
        Transition toCheck = new TransitionImpl(card, playersPositions[playerID], toField);
        if (transitions.contains(toCheck))
        {
            return true;
        }
        return false;
    }

    @Override
    public void setStartField(int playerId, int field) {
        playersPositions[playerId] = field;
    }

    @Override
    public int getPositionOfPlayer(int playerId) {
        return playersPositions[playerId];
    }

    @Override
    public void setPositionOfPlayer(int playerID, int toField) {
        playersPositions[playerID] = toField;
    }
}
