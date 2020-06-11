package com.example.scotlandyard.presenterLayer;

import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.example.scotlandyard.Client.Messages.AskPlayerForTurn;
import com.example.scotlandyard.Client.Messages.BaseMessage;
import com.example.scotlandyard.Client.Messages.ReadyMessage;
import com.example.scotlandyard.Client.Messages.SendLobbyID;
import com.example.scotlandyard.Client.Messages.SendPlayerIDtoClient;
import com.example.scotlandyard.Client.Messages.SendRoleMessage;
import com.example.scotlandyard.Client.Messages.StartGameMessage;
import com.example.scotlandyard.Client.Messages.TextMessage;
import com.example.scotlandyard.Client.Messages.TravellogMessage;
import com.example.scotlandyard.Client.Messages.TurnMessage;
import com.example.scotlandyard.Client.Messages.UpdatePlayersPosition;
import com.example.scotlandyard.Client.Messages.UpdateTicketCount;
import com.example.scotlandyard.Client.Messages.UsernameMessage;
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

    private String role;

    private int lobbyID;

    private String username;

    private TextView log;

    private boolean verbunden = false;

    private int playerID;

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
            client.registerClass(AskPlayerForTurn.class);
            client.registerClass(TravellogMessage.class);
            client.registerClass(UsernameMessage.class);
            client.registerClass(UpdatePlayersPosition.class);
            client.registerClass(StartGameMessage.class);
            client.registerClass(SendRoleMessage.class);
            client.registerClass(SendLobbyID.class);
            client.registerClass(SendPlayerIDtoClient.class);
            client.registerClass(ReadyMessage.class);
            client.registerClass(UpdateTicketCount.class);
            client.registerClass(TravelLog.class);


            registerCallback();

            try {
                client.connect(hostname);
            } catch (IOException e) {
                Log.e("Fehler:", "Beim Verbinden mit dem Server");
            }
            this.verbunden = true;
        }
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

                // Spieler wird nach einem Zug gefragt
                game.askPlayerforTurn();
            }

            if (nachrichtVomServer instanceof SendLobbyID) {
                SendLobbyID message = (SendLobbyID) nachrichtVomServer;
                setLobbyID(message.getLobbyID());
            }

            if (nachrichtVomServer instanceof UpdatePlayersPosition) {
                UpdatePlayersPosition updatePlayersPosition = (UpdatePlayersPosition) nachrichtVomServer;
                Log.d("Server:", "PlayersPosition: " + updatePlayersPosition.getPlayerId() + " ToField: " + updatePlayersPosition.getToField()
                        + " LobbyID: " + updatePlayersPosition.getLobbyId());

            }

            if (nachrichtVomServer instanceof SendPlayerIDtoClient) {
                SendPlayerIDtoClient sendPlayerIDtoClient = (SendPlayerIDtoClient) nachrichtVomServer;
                Log.d("Server:", "PlayerID: " + sendPlayerIDtoClient.getId());
                this.playerID = sendPlayerIDtoClient.getId();
            }

            if (nachrichtVomServer instanceof UpdatePlayersPosition){

                // Updates the position of a player on the map
                UpdatePlayersPosition msg =(UpdatePlayersPosition)nachrichtVomServer;
                updatePositionOfPlayerOnMap(msg.getPlayerId(),msg.getToField());


            }

            if (nachrichtVomServer instanceof UpdateTicketCount){
                UpdateTicketCount msg=(UpdateTicketCount)nachrichtVomServer;
                Log.d("TICKET","type:"+msg.getType()+" count:"+msg.getCount());
                updateTicketCount(msg.getType(),msg.getCount());
            }

            if (nachrichtVomServer instanceof TravellogMessage){
                TravellogMessage msg=(TravellogMessage)nachrichtVomServer;
                updateTravellogMenu(msg.getTravelLog(),msg.getRound());
            }

        });


    }

    public void sendMessagetoServer(String text) {
        TextMessage message = new TextMessage(username + ": " + text);
        client.sendMessage(message);
        // updateLog(message.getText());
    }

    public void sendTurn(TurnMessage message) {
        TurnMessage msg = new TurnMessage(playerID, message.getToField(), 0, message.getCard());
        client.sendMessage(msg);
    }

    public void sendReady() {
        ReadyMessage message = new ReadyMessage();
        client.sendMessage(message);

    }

    public void sendRole() {
        SendRoleMessage message = new SendRoleMessage();
        message.setText(role);
        message.setPlayerId(playerID);
        message.setName(getUsername());
        message.setLobbyId(getLobbyID());
        client.sendMessage(message);

    }

    public void sendUsername() {
        UsernameMessage msg = new UsernameMessage(username);
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

        /*list.add(text);
        if (list.size() == 15) {
            list.remove(0);
        }
        String toAdd = "";
        for (int i = 0; i < list.size(); i++) {
            toAdd += list.get(i) + "\n";
        }
        log.setText(toAdd);*/
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
        if (round == 2 || round == 7 || round == 12 || round == 17 || travelLog.isCaughtCheating()) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getLobbyID() {
        return lobbyID;
    }

    public void setLobbyID(int lobbyID) {
        this.lobbyID = lobbyID;
    }

    public void updatePositionOfPlayerOnMap(int id, int toField)
    {
        game.drawPlayer(id,toField);
    }

    public void updateTicketCount(String type, int count){
        game.updateCount(type,count);
    }
}
