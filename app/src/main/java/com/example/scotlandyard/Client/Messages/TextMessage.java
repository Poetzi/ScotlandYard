package com.example.scotlandyard.Client.Messages;


/**
 * Message-Class for Client-Server-communication. Message class for the Chat
 */
public class TextMessage extends BaseMessage {
    //Text
    private String text;
    //Lobby ID
    private int lobbyID;

    /**
     * Empty constructor
     */
    public TextMessage() {
    }

    /**
     * Constructor for TextMessage
     *
     * @param text Chat-Message
     */
    public TextMessage(String text) {
        this.text = text;
    }

    /**
     * Constructor for TextMessage
     *
     * @param text    Chat-Message
     * @param lobbyID Lobby ID
     */
    public TextMessage(String text, int lobbyID) {
        this.text = text;
        this.lobbyID = lobbyID;
    }

    //Getter and Setter
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLobbyID() {
        return lobbyID;
    }

    public void setLobbyID(int lobbyID) {
        this.lobbyID = lobbyID;
    }

    //toString method is overwritten
    @Override
    public String toString() {
        return text;
    }
}