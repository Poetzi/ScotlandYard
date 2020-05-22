package com.example.scotlandyard.presenterLayer;

import android.util.Log;
import android.widget.TextView;


import com.example.scotlandyard.Client.Messages.AskPlayerForTurn;
import com.example.scotlandyard.Client.Messages.BaseMessage;
import com.example.scotlandyard.Client.Messages.TextMessage;
import com.example.scotlandyard.Client.Messages.TurnMessage;
import com.example.scotlandyard.Client.MyKryoClient;
import com.example.scotlandyard.viewLayer.User;
import com.example.scotlandyard.viewLayer.gameActivity;

import java.io.IOException;

public class Presenter{

    // Singleton
    private static Presenter presenter;

    private MyKryoClient client;
    // Logik z.B: Spielelogik
    private User user;

    private TextView log;

    private boolean verbunden = false;

    private gameActivity game;

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
            client.registerClass(TurnMessage.class);
            client.registerClass(AskPlayerForTurn.class);

            client.registerCallback(nachrichtVomServer -> {
                if (nachrichtVomServer instanceof TextMessage) {
                    TextMessage message = (TextMessage) nachrichtVomServer;
                    updateLog(message.toString());
                }

                if(nachrichtVomServer instanceof AskPlayerForTurn){
                    AskPlayerForTurn message = (AskPlayerForTurn) nachrichtVomServer;
                    System.out.println(message.getText());
                    if(message.getText().equalsIgnoreCase("yes") || message.getText().equalsIgnoreCase("no")){
                        game.check = false;
                        if(message.getText().equalsIgnoreCase("yes"))
                            game.confirm = "yes";
                    }
                }
            });

            try {
                client.connect(hostname);
            } catch (IOException e) {
                Log.e("Fehler:", "Beim Verbinden mit dem Server");
            }
            this.verbunden = true;
        }
    }

    public void sendMessagetoServer(String text) {
        TextMessage message = new TextMessage(user.getName() + ": " + text);
        client.sendMessage(message);
    }

    public void sendTurn(TurnMessage message){
        TurnMessage msg = new TurnMessage(user.getId(),message.getToField(),0,message.getCard());
        client.sendMessage(msg);
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

    public gameActivity getGame() {
        return game;
    }

    public void setGame(gameActivity game) {
        this.game = game;
    }
}
