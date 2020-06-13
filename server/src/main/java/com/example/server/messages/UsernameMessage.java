package com.example.server.messages;

public class UsernameMessage extends BaseMessage {

    //Username
    private String username;

    private int playerId;

    /**
     * Empty constructor
     */
    public UsernameMessage() {
    }

    /**
     * Constructor for Username Message
     *
     * @param username Username
     */
    public UsernameMessage(String username, int playerId) {
        this.username = username;
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    //Getter and Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



}
