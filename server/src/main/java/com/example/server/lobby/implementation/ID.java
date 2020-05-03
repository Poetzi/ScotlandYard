package com.example.server.lobby.implementation;

import com.esotericsoftware.kryonet.Connection;

public class ID {
    public int id;
    public Connection name;

    public ID( Connection name){
        id = id+1;
        this.name = name;
    }
}
