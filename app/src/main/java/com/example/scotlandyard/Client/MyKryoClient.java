package com.example.scotlandyard.Client;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.example.scotlandyard.viewLayer.Callback;
import com.example.scotlandyard.Client.Messages.BaseMessage;
import com.example.scotlandyard.viewLayer.Presenter;
import com.example.scotlandyard.Client.Messages.BaseMessage;
import com.example.scotlandyard.viewLayer.Callback;


import java.io.IOException;

public class MyKryoClient {
    private Client client;
    private Callback<BaseMessage> callback;


    public MyKryoClient() {
        client = new Client();
    }

    public void registerClass(Class c) {
        client.getKryo().register(c);
    }

    public void connect(String host) throws IOException {
        client.start();
        client.connect(5000, host, Ports.TCP);

        client.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (callback != null && object instanceof BaseMessage)
                    callback.callback((BaseMessage) object);
            }
        });
    }

    public void registerCallback(Callback<BaseMessage> callback) {
        this.callback = callback;
    }

    public void sendMessage(BaseMessage message) {
        client.sendTCP(message);
    }

}