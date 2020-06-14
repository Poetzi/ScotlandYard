package com.example.scotlandyard.Client.Messages;

/**
 * Message-Class for Client-Server-communication. Updates the ticket count.
 */
public class UpdateTicketCount extends BaseMessage {
    //Count
    private int count;
    //Type
    private String type;
    //Player ID
    private int playerId;

    /**
     * Empty constructor
     */
    public UpdateTicketCount() {
    }

    /**
     * Constructor for UpdateTicketCount
     *
     * @param count    Count
     * @param type     Type
     * @param playerId Player ID
     * @param lobbyId  Lobby ID
     */
    public UpdateTicketCount(int count, String type, int playerId) {
        this.count = count;
        this.type = type;
        this.playerId = playerId;
    }

    //Getter and Setter
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
