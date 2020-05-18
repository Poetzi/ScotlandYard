package com.example.server.messages;

public class TurnMessage extends BaseMessage {
    private int playerId;
    private int toField;
    private String Card;

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
}
