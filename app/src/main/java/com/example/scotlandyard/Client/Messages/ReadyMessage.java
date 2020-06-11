package com.example.scotlandyard.Client.Messages;

public class ReadyMessage extends BaseMessage {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
