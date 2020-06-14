package com.example.server.messages;

import com.example.server.game.players.TravelLog;

public class TravellogMessage extends BaseMessage {
    private TravelLog travelLog;
    private int round;


    public TravellogMessage() {
    }

    public TravellogMessage(TravelLog travelLog, int round) {
        this.travelLog = travelLog;
        this.round = round;

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

}
