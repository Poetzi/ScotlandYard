package com.example.server.lobby.implementation;

import com.esotericsoftware.kryo.Kryo;
import com.example.server.lobby.interfaces.Lobby;

import java.util.ArrayList;

public class LobbyImpl implements Lobby {
    private ArrayList<ID> players = new ArrayList<ID>();
    private boolean isOpen = false;
    public int playerCount =0;


    @Override
    public void addPlayertoGame(ID id) {
        players.add(id);
        playerCount++;
        if(playerCount==6) startGame();
    }

    @Override
    public int getPlayerCount() {
        return playerCount;
    }



    @Override
    public void startGame() {
        Runnable runnable =    new Runnable(){
            public void run(){

            }
        };
        Thread t = new Thread(runnable);
        t.start();

    }

    @Override
    public void removePlayerfromGame(ID id) {
        players.remove(id);
    }

    @Override
    public boolean isLobbyOpen(){
        return isOpen;
    }
}
