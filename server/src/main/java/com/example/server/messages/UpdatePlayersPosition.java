package com.example.server.messages;

public class UpdatePlayersPosition extends BaseMessage {
    private int playerId;
    private int toField;
    private int lobbyId;

    public UpdatePlayersPosition(int playerId, int toField, int lobbyId) {
        this.playerId = playerId;
        this.toField = toField;
        this.lobbyId = lobbyId;
    }


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
