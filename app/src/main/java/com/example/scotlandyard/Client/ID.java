package com.example.scotlandyard.Client;

import com.esotericsoftware.kryonet.Connection;

public class ID {
    public int id;
    public Connection name;
    public boolean isHere = true;

    public ID(int id, Connection name){
        this.id = id;
        this.name = name;
    }
}