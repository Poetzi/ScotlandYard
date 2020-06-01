package com.example.server.messages;

public class UsernameMessage extends BaseMessage {

    private String username;

    public UsernameMessage() {
    }


    public UsernameMessage(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
