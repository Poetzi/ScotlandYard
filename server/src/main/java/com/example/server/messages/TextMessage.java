package com.example.server.messages;

/**
 * Eine Klasse um einen String zu versenden z.B: für die Chatfunktion
 */
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