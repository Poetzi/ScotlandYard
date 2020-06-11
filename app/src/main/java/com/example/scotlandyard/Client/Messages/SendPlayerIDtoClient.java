package com.example.scotlandyard.Client.Messages;

/**
 * Message-Class for Client-Server-communication. Server sends an individual ID to every client.
 */
public class SendPlayerIDtoClient extends BaseMessage {
    //Player-ID
    private int id;

    //Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
