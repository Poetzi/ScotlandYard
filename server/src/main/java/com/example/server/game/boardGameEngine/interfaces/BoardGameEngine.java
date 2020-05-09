package com.example.server.game.boardGameEngine.interfaces;


import com.example.server.game.players.interfaces.Player;
import com.example.server.game.transitions.interfaces.Transition;
import com.example.server.lobby.interfaces.Lobby;

public interface BoardGameEngine {

    /*
        Methode, um einen neuen Spieler hinzuzufügen
     */
    void addPlayer(String name, int field);

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
        Überprüft ob die Bedingungen für einen Sieg erfüllt sind
     */
    boolean checkWinningCondition();


    /*
        initialisiert ein Lobby Object
     */
    void initLobby(Lobby lobby);






}
