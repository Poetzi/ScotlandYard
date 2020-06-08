package com.example.scotlandyard.Client.Messages;

public class SendRoleMessage extends BaseMessage {
    private String text;

    private int lobbyID;

    public int getLobbyID() {
        return lobbyID;
    }

    public void setLobbyID(int lobbyID) {
        this.lobbyID = lobbyID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
