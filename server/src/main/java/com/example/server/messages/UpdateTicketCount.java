package com.example.server.messages;

public class UpdateTicketCount extends BaseMessage {
    private int count;
    private String type;
    private int playerId;

    public UpdateTicketCount() {
    }

    public UpdateTicketCount(int count, String type, int playerId) {
        this.count = count;
        this.type = type;
        this.playerId = playerId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

}
