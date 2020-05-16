package com.example.scotlandyard.viewLayer;

import android.widget.TextView;


import com.example.scotlandyard.Client.Messages.BaseMessage;
import com.example.scotlandyard.Client.Messages.TextMessage;
import com.example.scotlandyard.Client.MyKryoClient;

import java.io.IOException;

public class Presenter{

    // Singleton
    private static Presenter presenter;

    private MyKryoClient client;
    // Logik z.B: Spielelogik
    private User user;

    private TextView log;

    private boolean verbunden = false;

    // private Konstruktor
    private Presenter() {
        client = new MyKryoClient();
    }


    // Singleton wird zurückgegeben
    public static synchronized Presenter getInstance() {
        if (Presenter.presenter == null) {
            Presenter.presenter = new Presenter();
        }
        return Presenter.presenter;
    }

    public void connectToServer(String hostname) {
        if(verbunden == false) {
            client.registerClass(BaseMessage.class);
            client.registerClass(TextMessage.class);

            client.registerCallback(nachrichtVomServer -> {
                if (nachrichtVomServer instanceof TextMessage) {
                    TextMessage message = (TextMessage) nachrichtVomServer;
                    updateLog(message.toString());
                }
            });

            try {
                client.connect(hostname);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.verbunden = true;
        }
    }

    public void sendMessagetoServer(String text) {
        TextMessage message = new TextMessage(user.getName() + ": " + text);
        client.sendMessage(message);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void updateLog(String text) {
        String prev = log.getText().toString();
        String toAdd = text;
        String newlog = prev + toAdd + "\n";

        log.setText(newlog);
    }

    public TextView getLog() {
        return log;
    }

    public void setLog(TextView log) {
        this.log = log;
    }
}
