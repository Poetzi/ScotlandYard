package com.example.server.messages;

public class TurnMessage extends BaseMessage {
    private int playerId;
    private int toField;
    private int lobbyId;
    private String Card;
    private boolean cheat;



    public TurnMessage() {
    }

    public TurnMessage(int playerId, int toField, int lobbyId, String card,boolean cheat) {
        this.playerId = playerId;
        this.lobbyId = lobbyId;
        Card = card;
        this.cheat = cheat;
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

    public boolean isCheat() {
        return cheat;
    }

    public void setCheat(boolean cheat) {
        this.cheat = cheat;
    }
}
