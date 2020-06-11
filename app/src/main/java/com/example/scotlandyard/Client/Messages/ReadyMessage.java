package com.example.scotlandyard.Client.Messages;

/**
 * Message-Class for Client-Server-communication. Client sends message to server that it is ready.
 */
public class ReadyMessage extends BaseMessage {
    //Ready-Message
    private String text;

    //Getter and Setter
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
