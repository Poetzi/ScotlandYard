package com.example.scotlandyard.Client.Messages;

/**
 * Message-Class for Client-Server-communication. Server asks Clients for their
 * next turn.
 */
public class AskPlayerForTurn extends BaseMessage {
    //Player ID
    private int id;
    //Turn-Message
    private String text;
    //Lobby ID
    private int lobbyID;
    private int round;


    /**
     * Constructor where the information gets assigned.
     *
     * @param id      Player ID
     * @param text    Turn-Message
     * @param lobbyID Lobby ID
     */
    public AskPlayerForTurn(int id, String text, int lobbyID) {
        this.id = id;
        this.text = text;
        this.lobbyID = lobbyID;
    }

    /**
     * Empty constructor.
     */
    public AskPlayerForTurn() {

    }

    //Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    //Override of toString-Method.
    @Override
    public String toString() {
        return text;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }
}