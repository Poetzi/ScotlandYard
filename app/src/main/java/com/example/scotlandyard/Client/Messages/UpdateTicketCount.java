package com.example.scotlandyard.Client.Messages;

public class UpdateTicketCount extends BaseMessage {
    private int count;
    private String type;
    private int playerId;
    private int lobbyId;

    public UpdateTicketCount() {
    }

    public UpdateTicketCount(int count, String type, int playerId, int lobbyId) {
        this.count = count;
        this.type = type;
        this.playerId = playerId;
        this.lobbyId = lobbyId;
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

    public int getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(int lobbyId) {
        this.lobbyId = lobbyId;
    }
}
