package com.example.server.game.gameBoard.implementation;



import com.example.server.game.gameBoard.interfaces.GameBoard;
import com.example.server.game.transitions.implementation.TransitionImpl;
import com.example.server.game.transitions.interfaces.Transition;

import java.util.ArrayList;
import java.util.HashSet;

public class GameBoardImpl implements GameBoard {

    /*
       Jede Stelle im Arrray steht für einen Spieler
     */
    int [] playersPositions = new int[6];
    /*
        Speichert die Anzahl der Spielfelder
     */
    HashSet<Integer> fields; //(Liste oder Set von Field)
    /*
        Speichert die Wege von einem Spielfeld zum anderen
     */
    ArrayList<Transition> transitions = new ArrayList<>(); //(Liste oder Set)


    @Override
    public void addFieldWithTransition(int fromField, int toField, String rule) {
        Transition firstTransition = new TransitionImpl(rule,toField,fromField);
        Transition secondTransition = new TransitionImpl(rule,fromField,toField);

        transitions.add(firstTransition);
        transitions.add(secondTransition);
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
        for (int i = 0; i <transitions.size() ; i++) {
            if (transitions.get(i).getToField() == toField) {
                if (transitions.get(i).getFromField() == playersPositions[playerID]) {
                    if (transitions.get(i).getName().equalsIgnoreCase(card)) {
                        System.out.println("Gameboard True");
                        return true;
                    }
                }
            }
        }
        System.out.println("player position"+playersPositions[playerID]);
        System.out.println("Gameboard False");

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
