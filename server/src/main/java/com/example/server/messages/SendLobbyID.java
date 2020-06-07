package com.example.server.messages;

public class SendLobbyID extends BaseMessage {
   private int lobbyID;

    public int getLobbyID() {
        return lobbyID;
    }

    public void setLobbyID(int lobbyID) {
        this.lobbyID = lobbyID;
    }
}
