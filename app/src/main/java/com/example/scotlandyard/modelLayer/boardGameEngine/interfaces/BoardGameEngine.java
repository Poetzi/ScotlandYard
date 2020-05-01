package com.example.scotlandyard.modelLayer.boardGameEngine.interfaces;

import com.example.scotlandyard.modelLayer.players.interfaces.Player;
import com.example.scotlandyard.modelLayer.transitions.interfaces.Transition;

public interface BoardGameEngine {

    /*
        Methode, um einen neuen Spieler hinzuzufügen
     */
    void addPlayer(String name);

    /*
        Einstellungen für ein neues Spiel werden hier festgelegt,
        wie den Spielern ihre Bus, Bahn und Taxi Tickets zuzuteilen und…
     */
    void setupNewGame();

    /*
        Startet das Spiel
     */
    void startGame();

    /*
        Ist für die Logik einer Runde innerhalb des Spiels zuständig
     */
    void playOneRound();

    /*
        Macht einen Zug für einen Spieler
     */
    void drawForPlayer(Player player);

    /*
        Bewegt einen Spieler auf dem Spielbrett
     */
    void movePlayer(int playerId, Transition withTransition, int tofield);

    /*
        Überprüft ob die Bedingungen für einen Sieg erfüllt sind
     */
    boolean checkWinningCondition();

    /*
        Überprüft ob die Regeln eingehalten wurden sind
     */
    boolean checkRules();
}
