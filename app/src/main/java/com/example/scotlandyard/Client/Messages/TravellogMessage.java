package com.example.scotlandyard.Client.Messages;


import com.example.scotlandyard.modelLayer.TravelLog;

/**
 * Message-Class for Client-Server-communication. Travel-log is send to the server.
 */
public class TravellogMessage extends BaseMessage {
    //Player-ID
    private int playerID;
    //Travel-log
    private TravelLog travelLog;
    //Round-number
    private int round;
    //Lobby-ID
    private int lobbyID;

    /**
     * Empty constructor.
     */
    public TravellogMessage() {
    }

    /**
     * Constructor for Travel-log message
     *
     * @param id        Player ID
     * @param travelLog Travel-log
     * @param round     Round-number
     * @param lobbyID   Lobby ID
     */
    public TravellogMessage(int id, TravelLog travelLog, int round, int lobbyID) {
        this.playerID = id;
        this.travelLog = travelLog;
        this.round = round;
        this.lobbyID = lobbyID;
    }

    //Getter and Setter
    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public TravelLog getTravelLog() {
        return travelLog;
    }

    public void setTravelLog(TravelLog travelLog) {
        this.travelLog = travelLog;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getLobbyID() {
        return lobbyID;
    }

    public void setLobbyID(int lobbyID) {
        this.lobbyID = lobbyID;
    }
}
