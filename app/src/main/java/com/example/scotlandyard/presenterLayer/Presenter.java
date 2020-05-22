package com.example.scotlandyard.presenterLayer;

import android.util.Log;
import android.view.Menu;
import android.widget.TextView;


import com.example.scotlandyard.Client.Messages.BaseMessage;
import com.example.scotlandyard.Client.Messages.TextMessage;
import com.example.scotlandyard.Client.Messages.TravellogMessage;
import com.example.scotlandyard.Client.Messages.TurnMessage;
import com.example.scotlandyard.Client.MyKryoClient;
import com.example.scotlandyard.modelLayer.players.TravelLog;
import com.example.scotlandyard.viewLayer.User;

import java.io.IOException;

public class Presenter{

    // Singleton
    private static Presenter presenter;

    private MyKryoClient client;
    // Logik z.B: Spielelogik
    private User user;

    private TextView log;

    private boolean verbunden = false;

    private Menu travellogMenu;

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
            client.registerClass(TravellogMessage.class);

            client.registerCallback(nachrichtVomServer -> {
                if (nachrichtVomServer instanceof TextMessage) {
                    TextMessage message = (TextMessage) nachrichtVomServer;
                    updateLog(message.toString());
                }else if (nachrichtVomServer instanceof TravellogMessage){
                    TravelLog travelLog=((TravellogMessage) nachrichtVomServer).getTravelLog();
                    updateTravellogMenu(travelLog,((TravellogMessage) nachrichtVomServer).getRound());
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
        updateLog(message.getText());
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

    public void updateTravellogMenu(TravelLog travelLog, int round){
        String transport=travelLog.getTicket();
        travellogMenu.add(Menu.NONE, round, Menu.NONE, transport);

    }

    public Menu getTravellogMenu() {
        return travellogMenu;
    }

    public void setTravellogMenu(Menu travellogMenu) {
        this.travellogMenu = travellogMenu;
    }
}
