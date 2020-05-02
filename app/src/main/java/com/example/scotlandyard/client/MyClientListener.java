package com.example.scotlandyard.client;

import android.util.Log;
import android.widget.TextView;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.example.scotlandyard.modelLayer.boardGameEngine.interfaces.BoardGameEngine;

import java.util.Date;

public class MyClientListener extends Listener {
    private MyKryoClient client;
    private BoardGameEngine clientEngine;
    private boolean messageReceived = false;
    private TextView textView;


    public void init(MyKryoClient client)
    {
        this.client = client;
    }

    public void connected(Connection connection)
    {
        Log.d("Client: ", "Verbunden mit dem main.java.Server");
        //String buffer = textView.getText().toString();
        //buffer = buffer +"\nClient: Verbunden mit dem main.java.Server";
        //textView.setText(buffer);
    }

    public void disconnected(Connection connection)
    {
        Log.d("Client: ", "Verbindung mit dem main.java.Server wurde getrennt");
        //String buffer = textView.getText().toString();
        //buffer = buffer +"\nClient: Verbindung mit dem main.java.Server wurde getrennt";
        //textView.setText(buffer);
    }

    public void received(Connection connection, Object object)
    {
        if(object instanceof Message)
        {
            Message message = (Message) object;
            client.getChatClient().anzeigen(message.message);
        }
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
