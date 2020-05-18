package com.example.scotlandyard.Client.Messages;

public class AskPlayerForTurn extends BaseMessage {
    private int id;
    private String text;
    private int lobbyID;

    public AskPlayerForTurn(int id, String text, int lobbyID) {
        this.id = id;
        this.text = text;
        this.lobbyID = lobbyID;
    }

    public AskPlayerForTurn() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}