package com.example.server.lobby.implementation;

import com.esotericsoftware.kryonet.Connection;

public class ID {
    public int id;
    public Connection name;
    public String nickname;

    public ID(Connection name, String username) {
        id = id + 1;
        this.name = name;
        nickname = username;

    }
}
