package com.example.scotlandyard.Client.Messages;


import com.example.scotlandyard.modelLayer.players.TravelLog;

public class TravellogMessage extends BaseMessage {
    private TravelLog travelLog;
    private int round;
    private int lobbyID;

    public TravellogMessage() {
    }

    public TravellogMessage(TravelLog travelLog, int round, int lobbyID) {
        this.travelLog = travelLog;
        this.round = round;
        this.lobbyID = lobbyID;
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
