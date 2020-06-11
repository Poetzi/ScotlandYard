package com.example.scotlandyard.Client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.example.scotlandyard.viewLayer.Callback;
import com.example.scotlandyard.Client.Messages.BaseMessage;

import java.io.IOException;

/**
 * Class for setting up the Kryonet-Client
 */
public class MyKryoClient {
    //CLient
    private Client client;
    //Callback
    private Callback<BaseMessage> callback;

    /**
     * Constructor creates a new Client.
     */
    public MyKryoClient() {
        client = new Client();
    }

    /**
     * Method for registering the Message Classes.
     *
     * @param c Message class
     */
    public void registerClass(Class c) {
        client.getKryo().register(c);
    }

    /**
     * Method for connecting with the server.
     *
     * @param host IPv4-Adresse
     * @throws IOException Exception
     */
    public void connect(String host) throws IOException {
        //Client is started
        client.start();
        //Client connects with server
        client.connect(5000, host, com.example.scotlandyard.Client.Ports.TCP);
        //Client handles callback-messages from server with a listener.
        client.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (callback != null && object instanceof BaseMessage)
                    callback.callback((BaseMessage) object);
            }
        });
    }

    /**
     * Method for registering callbacks
     *
     * @param callback Callback
     */
    public void registerCallback(Callback<BaseMessage> callback) {
        this.callback = callback;
    }

    /**
     * Method for sending a message to the server
     *
     * @param message Message
     */
    public void sendMessage(BaseMessage message) {
        client.sendTCP(message);
    }

}

