package com.example.scotlandyard.Client.Messages;


import com.example.scotlandyard.modelLayer.players.TravelLog;

public class TravellogMessage extends BaseMessage {
    private int playerID;
    private TravelLog travelLog;
    private int round;
    private int lobbyID;

    public TravellogMessage(){}

    public TravellogMessage(int id, TravelLog travelLog, int round, int lobbyID){
        this.playerID=id;
        this.travelLog=travelLog;
        this.round=round;
        this.lobbyID=lobbyID;
    }

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
