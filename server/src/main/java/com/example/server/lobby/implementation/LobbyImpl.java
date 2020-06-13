package com.example.server.lobby.implementation;

import com.example.server.game.boardGameEngine.implementation.BoardGameEngineImpl;
import com.example.server.game.boardGameEngine.interfaces.BoardGameEngine;
import com.example.server.game.players.TravelLog;
import com.example.server.lobby.interfaces.Lobby;
import com.example.server.messages.AskPlayerForTurn;
import com.example.server.messages.TravellogMessage;
import com.example.server.messages.TurnMessage;
import com.example.server.messages.UpdatePlayersPosition;
import com.example.server.messages.UpdateTicketCount;

import java.util.ArrayList;

public class LobbyImpl  {


    private ArrayList<ID> players = new ArrayList<ID>();
    private boolean isOpen = true;
    public int playerCount = 0;

    private boolean allReady;
    private int playerReady = 0;


    private BoardGameEngineImpl game = BoardGameEngineImpl.getInstance();

    // ToDo
    private int lobbyID = 1;

    // Speichert ob auf einen Zug für einen Spieler gewartet wird


    public void askPlayerforTurn(int playerId) {
        AskPlayerForTurn askPlayerForTurn = new AskPlayerForTurn(playerId, "gib bitte einen Zug an", lobbyID);
        players.get(playerId).name.sendTCP(askPlayerForTurn);

    }



    public void updatePlayerPositionsToAllClients(int playerId, int toField) {
        UpdatePlayersPosition playersPosition = new UpdatePlayersPosition(playerId, toField, lobbyID);
        for (ID p : players) {
            p.name.sendTCP(playersPosition);
        }
    }


    public void updateTravellogToAllClients(TravelLog travelLog, int round) {
        TravellogMessage travellogMessage = new TravellogMessage(travelLog, round, lobbyID);
        for (ID id : players) {
            id.name.sendTCP(travellogMessage);
        }
    }


    public void updateTicketCount(int playerId, int count, String type) {
        UpdateTicketCount ticketCount = new UpdateTicketCount(count, type, playerId, lobbyID);
        players.get(playerId).name.sendTCP(ticketCount);
        System.out.println("Ticketcount wurde geschickt.");
    }


    public int getLobbyID() {
        return lobbyID;
    }


    public void setLobbyID(int lobbyID) {
        this.lobbyID = lobbyID;
    }




    public BoardGameEngineImpl getGame() {
        return game;
    }





    public boolean isAllReady() {
        return allReady;
    }







}
