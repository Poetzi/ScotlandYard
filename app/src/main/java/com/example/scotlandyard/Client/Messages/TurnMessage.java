package com.example.scotlandyard.Client.Messages;

/**
 * Message-Class for Client-Server-communication. Client sends the turn it chose to the server.
 */
public class TurnMessage extends BaseMessage {
    //Player-ID
    private int playerId;
    //Field-Variable
    private int toField;
    //Lobby-ID
    private int lobbyId;
    //Card
    private String Card;

    /**
     * Empty constructor
     */
    public TurnMessage() {
    }

    /**
     * Constructor for Turn-message class.
     *
     * @param playerId Player ID
     * @param toField  toField
     * @param lobbyId  Lobby ID
     * @param card     Card
     */
    public TurnMessage(int playerId, int toField, int lobbyId, String card) {
        this.playerId = playerId;
        this.toField = toField;
        this.lobbyId = lobbyId;
        Card = card;
    }

    //Getter und Setter
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getToField() {
        return toField;
    }

    public void setToField(int toField) {
        this.toField = toField;
    }

    public String getCard() {
        return Card;
    }

    public void setCard(String card) {
        Card = card;
    }

    public int getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(int lobbyId) {
        this.lobbyId = lobbyId;
    }
}