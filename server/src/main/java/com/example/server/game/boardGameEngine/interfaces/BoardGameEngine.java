package com.example.server.game.boardGameEngine.interfaces;

import com.example.server.game.players.interfaces.Player;
import com.example.server.lobby.interfaces.Lobby;
import com.example.server.messages.TurnMessage;

public interface BoardGameEngine {

    /**
     * Methode um einen MrX Spieler hinzuzufügen
     * @param name
     * @param id
     */
    public void addMrX(String name ,int id);

    /**
     * Methode um einen Detektiv hinzuzufügen
     * @param name
     * @param id
     */
    public void addDetektiv(String name ,int id);

    /*
     * Einstellungen für ein neues Spiel werden hier festgelegt, wie den Spielern
     * ihre Bus, Bahn und Taxi Tickets zuzuteilen und…
     */
    void setupNewGame();

    /*
     * Startet das Spiel
     */
    void startGame();

    /*
     * Ist für die Logik einer Runde innerhalb des Spiels zuständig
     */
    void playOneRound();

    /*
     * Macht einen Zug für einen Spieler
     */
    void drawForPlayer(Player player);

    /*
     * Überprüft ob die Bedingungen für einen Sieg erfüllt sind
     */
    boolean checkWinningCondition();

    /*
     * initialisiert ein Lobby Object
     */
    void initLobby(Lobby lobby);

    boolean checkIfMrXCheated();


    Lobby getLobby();

    void setLobby(Lobby lobby);



}
