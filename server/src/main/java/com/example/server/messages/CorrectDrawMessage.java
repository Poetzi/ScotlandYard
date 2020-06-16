package com.example.server.messages;

public class CorrectDrawMessage {
    boolean check;
    String ticket;

    public CorrectDrawMessage(){}

    public CorrectDrawMessage(boolean check,String ticket){
        this.check = check;
        this.ticket = ticket;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
