package com.example.scotlandyard.Client.Messages;


public class SendPlayerIDtoClient extends BaseMessage {
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
