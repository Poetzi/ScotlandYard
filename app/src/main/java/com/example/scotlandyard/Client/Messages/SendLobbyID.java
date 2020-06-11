package com.example.scotlandyard.Client.Messages;

/**
 * Message-Class for Client-Server-communication. Server sends lobby-ID to Client.
 */
public class SendLobbyID extends BaseMessage {
    //Lobby-ID
    private int lobbyID;

    public SendLobbyID() {

    }

    //Getter and Setter
    public int getLobbyID() {
        return lobbyID;
    }

    public void setLobbyID(int lobbyID) {
        this.lobbyID = lobbyID;
    }
}
