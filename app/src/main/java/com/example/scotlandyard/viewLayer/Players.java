package com.example.scotlandyard.viewLayer;

import com.example.scotlandyard.modelLayer.players.interfaces.Player;

public class Players {

    int x;
    int y;
    int playerId;
    int position;

    public Players(int x, int y, int playerId, int position){
        this.x = x;
        this.y = y;
        this.playerId = playerId;
        this.position = position;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
