package com.example.server.messages;

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

    public int getLobbyID() {
        return lobbyID;
    }

    public void setLobbyID(int lobbyID) {
        this.lobbyID = lobbyID;
    }

    @Override
    public String toString() {
        return text;
    }
}
