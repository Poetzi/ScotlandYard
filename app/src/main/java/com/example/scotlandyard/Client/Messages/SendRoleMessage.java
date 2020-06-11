package com.example.scotlandyard.Client.Messages;

/**
 * Message-Class for Client-Server-communication. Client sends the picked role to the server.
 */
public class SendRoleMessage extends BaseMessage {
    //Role-Message
    private String text;
    //Player ID
    private int playerId;
    //Player name
    private String name;
    //Lobby-ID
    private int lobbyId;

    //Getter and Setter
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

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
