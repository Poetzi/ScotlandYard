package com.example.scotlandyard.modelLayer.boardGameEngine.interfaces;

import com.example.scotlandyard.client.TurnMessage;
import com.example.scotlandyard.modelLayer.players.interfaces.Player;
import com.example.scotlandyard.modelLayer.transitions.interfaces.Transition;

public interface BoardGameEngine {

    /*
        Methode, um einen neuen Spieler hinzuzufügen
     */
    void addPlayer(String name);


    TurnMessage getTurnFromPlayer();


    /*
        Überprüft ob die Bedingungen für einen Sieg erfüllt sind
     */
    boolean checkWinningCondition();

    /*
        Überprüft ob die Regeln eingehalten wurden sind
     */
    boolean checkRules();
}
