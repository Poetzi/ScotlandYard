package com.example.scotlandyard.Client.Messages;

/**
 * Message-Class for Client-Server-communication. Server sends the Players an update for the positions.
 */
public class UpdatePlayersPosition extends BaseMessage {
    //Player ID
    private int playerId;
    //toField
    private int toField;
    //Lobby ID
    private int lobbyId;

    /**
     * Constructor for UpdatePlayersPosition.
     *
     * @param playerId Player ID
     * @param toField  toField
     * @param lobbyId  Lobby ID
     */
    public UpdatePlayersPosition(int playerId, int toField, int lobbyId) {
        this.playerId = playerId;
        this.toField = toField;
        this.lobbyId = lobbyId;
    }

    //Getter and Setter
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

    public int getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(int lobbyId) {
        this.lobbyId = lobbyId;
    }
}