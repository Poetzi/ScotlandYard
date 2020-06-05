package com.example.server.messages;

public class SendRoleMessage extends BaseMessage {
    private String text;

    private int lobbyId;

    public int getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(int lobbyId) {
        this.lobbyId = lobbyId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
