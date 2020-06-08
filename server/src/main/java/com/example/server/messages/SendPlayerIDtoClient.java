package com.example.server.messages;

public class SendPlayerIDtoClient extends BaseMessage {
   private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
