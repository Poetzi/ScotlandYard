package com.example.scotlandyard.presenterLayer;

import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.example.scotlandyard.Client.Messages.AskPlayerForTurn;
import com.example.scotlandyard.Client.Messages.BaseMessage;
import com.example.scotlandyard.Client.Messages.TextMessage;
import com.example.scotlandyard.Client.Messages.TravellogMessage;
import com.example.scotlandyard.Client.Messages.TurnMessage;
import com.example.scotlandyard.Client.Messages.UpdatePlayersPosition;
import com.example.scotlandyard.Client.Messages.UpdateTicketCount;
import com.example.scotlandyard.Client.MyKryoClient;
import com.example.scotlandyard.modelLayer.players.TravelLog;
import com.example.scotlandyard.viewLayer.User;
import com.example.scotlandyard.viewLayer.gameActivity;

import java.io.IOException;

public class Presenter {

    // Singleton
    private static Presenter presenter;

    private MyKryoClient client;
    // Logik z.B: Spielelogik
    private User user;

    private TextView log;

    private boolean verbunden = false;

    private gameActivity game;
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
        if (verbunden == false) {
            client.registerClass(BaseMessage.class);
            client.registerClass(TextMessage.class);
            client.registerClass(TurnMessage.class);
            client.registerClass(TravellogMessage.class);
            client.registerClass(AskPlayerForTurn.class);
            client.registerClass(UpdateTicketCount.class);
            client.registerClass(UpdatePlayersPosition.class);

            registerCallback();

            try {
                client.connect(hostname);
            } catch (IOException e) {
                Log.e("Fehler:", "Beim Verbinden mit dem Server");
            }
            this.verbunden = true;
        }
    }

    private void registerCallback() {
        client.registerCallback(nachrichtVomServer -> {
            if (nachrichtVomServer instanceof TextMessage) {
                TextMessage message = (TextMessage) nachrichtVomServer;
                updateLog(message.toString());
            }

            if (nachrichtVomServer instanceof AskPlayerForTurn) {
                AskPlayerForTurn message = (AskPlayerForTurn) nachrichtVomServer;
                Log.d("Server:", message.getText());
                if (message.getText().equalsIgnoreCase("yes") || message.getText().equalsIgnoreCase("no")) {
                    game.check = false;
                    if (message.getText().equalsIgnoreCase("yes"))
                        game.confirm = "yes";
                }
            }
            //Update Ticketanzahl
            if (nachrichtVomServer instanceof UpdateTicketCount){
                UpdateTicketCount message=(UpdateTicketCount) nachrichtVomServer;
                game.updateTicketCount(message.getTicketCount(),message.getTicketType());
            }
            //Update Travellog
            if (nachrichtVomServer instanceof TravellogMessage){
                TravellogMessage message=(TravellogMessage) nachrichtVomServer;
                updateTravellogMenu(message.getTravelLog(),message.getRound());
            }
            //Update Position von Mitspielern
            if (nachrichtVomServer instanceof UpdatePlayersPosition){
                UpdatePlayersPosition message=(UpdatePlayersPosition) nachrichtVomServer;
                game.updatePlayerPosition(message.getToField(),message.getPlayerId());
            }
        });

    }

    public void sendMessagetoServer(String text) {
        TextMessage message = new TextMessage(user.getName() + ": " + text);
        client.sendMessage(message);
        updateLog(message.getText());
    }

    public void sendTurn(TurnMessage message) {
        TurnMessage msg = new TurnMessage(user.getId(), message.getToField(), 0, message.getCard());
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

    public void updateTravellogMenu(TravelLog travelLog, int round) {
        String transport = travelLog.getTicket();

        if (round == 3 || round == 8 || round == 13 || round == 18 || travelLog.isCaughtCheating()) {
            //In Runde 3,8,13 und 18 wird wie nach den Regeln die Position von Mister X bekannt gegeben.
            //Oder wenn er beim Schummeln erwischt wurde.
            travellogMenu.add(Menu.NONE, round, Menu.NONE, transport + ", Position:" + round);
        } else {
            travellogMenu.add(Menu.NONE, round, Menu.NONE, transport);
        }
    }

    public Menu getTravellogMenu() {
        return travellogMenu;
    }

    public void setTravellogMenu(Menu travellogMenu) {
        this.travellogMenu = travellogMenu;
    }
}
