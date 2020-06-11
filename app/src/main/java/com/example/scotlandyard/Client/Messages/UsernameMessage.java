package com.example.scotlandyard.Client.Messages;

/**
 * Message-Class for Client-Server-communication. Client sends the username to the Server.
 */
public class UsernameMessage extends BaseMessage {

    //Username
    private String username;

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
    public UsernameMessage(String username) {
        this.username = username;
    }

    //Getter and Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
