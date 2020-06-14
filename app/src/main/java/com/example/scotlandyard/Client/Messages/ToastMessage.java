package com.example.scotlandyard.Client.Messages;

public class ToastMessage extends BaseMessage{

    int playerId = 0;
    String msg = " ";

    public ToastMessage(){}

    public ToastMessage(int id, String msg){
        playerId = id;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }



    public int getPlayerId() {
        return playerId;
    }

}
