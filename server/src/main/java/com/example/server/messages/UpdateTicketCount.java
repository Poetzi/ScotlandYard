package com.example.server.messages;

public class UpdateTicketCount extends BaseMessage {
    private int playerID;
    private int ticketCount;
    private String ticketType;
    private int lobbyID;

    public UpdateTicketCount(int playerID, int ticketCount, String ticketType, int lobbyID) {
        this.playerID = playerID;
        this.ticketCount = ticketCount;
        this.ticketType = ticketType;
        this.lobbyID = lobbyID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int getLobbyID() {
        return lobbyID;
    }

}
