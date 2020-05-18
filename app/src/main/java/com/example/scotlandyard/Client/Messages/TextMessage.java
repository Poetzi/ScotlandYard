package com.example.scotlandyard.Client.Messages;


/**
 * Eine Klasse um einen String zu versenden z.B: für die Chatfunktion
 */
public class TextMessage extends BaseMessage {

    public TextMessage() {
    }


    public TextMessage(String text) {
        this.text = text;
    }

    public TextMessage(String text, int lobbyID) {
        this.text = text;
        this.lobbyID = lobbyID;
    }

    private String text;
    private int lobbyID;


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

    @Override
    public String toString() {
        return text;
    }
}