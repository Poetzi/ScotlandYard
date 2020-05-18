package com.example.server.messages;

public class AskPlayerForTurn extends BaseMessage {
    private int id;
    private String text;

    public AskPlayerForTurn(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public AskPlayerForTurn() {
    }

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

    @Override
    public String toString() {
        return text;
    }
}
