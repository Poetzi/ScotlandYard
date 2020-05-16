package com.example.server.messages;

public class TextMessage extends BaseMessage {

    public TextMessage() {
    }

    public TextMessage(String text) {
        this.text = text;
    }

    public String text;

    @Override
    public String toString() {
        return text;
    }
}