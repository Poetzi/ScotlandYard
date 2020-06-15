package com.example.scotlandyard.presenterLayer;

import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.example.scotlandyard.Client.Messages.AskPlayerForTurn;
import com.example.scotlandyard.Client.Messages.BaseMessage;
import com.example.scotlandyard.Client.Messages.LoserMessage;
import com.example.scotlandyard.Client.Messages.ReadyMessage;
import com.example.scotlandyard.Client.Messages.SendLobbyID;
import com.example.scotlandyard.Client.Messages.SendPlayerIDtoClient;
import com.example.scotlandyard.Client.Messages.SendRoleMessage;
import com.example.scotlandyard.Client.Messages.StartGameMessage;
import com.example.scotlandyard.Client.Messages.TextMessage;
import com.example.scotlandyard.Client.Messages.ToastMessage;
import com.example.scotlandyard.Client.Messages.TravellogMessage;
import com.example.scotlandyard.Client.Messages.TurnMessage;
import com.example.scotlandyard.Client.Messages.UpdatePlayersPosition;
import com.example.scotlandyard.Client.Messages.UpdateTicketCount;
import com.example.scotlandyard.Client.Messages.UsernameMessage;
import com.example.scotlandyard.Client.Messages.WinnerMessage;
import com.example.scotlandyard.Client.MyKryoClient;
import com.example.scotlandyard.modelLayer.TravelLog;
import com.example.scotlandyard.viewLayer.User;
import com.example.scotlandyard.viewLayer.gameActivity;

import java.io.IOException;

/**
 * Mediator class for communicating between Android-Activities and the Kryo-Client.
 * Implemented as Singleton-Pattern
 */
public class Presenter {

    // Singleton
    private static Presenter presenter;
    //Kryo-Client
    private MyKryoClient client;
    //User
    private User user;
    //Role
    private String role;
    //Lobby-ID
    private int lobbyID;
    //Username
    private String username;
    //Log-TextView
    private TextView log;
    //Connection-bool
    private boolean verbunden = false;
    //Player ID
    private int playerID=0;
    //Game-Activity
    private gameActivity game;
    //Travel-log
    private Menu travellogMenu;

    /**
     * Private Constructor for Singleton Pattern
     */
    private Presenter() {
        //New client is created
        client = new MyKryoClient();
    }

    /**
     * Singleton is returned
     *
     * @return Presenter-Singleton
     */
    public static synchronized Presenter getInstance() {
        //If presenter is not yet initialized, create a new one.
        if (Presenter.presenter == null) {
            Presenter.presenter = new Presenter();
        }
        //If the presenter is already initialized, return the presenter.
        return Presenter.presenter;
    }

    /**
     * Method for registering Message-Classes
     *
     * @param hostname IPv4-address
     */
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
            client.registerClass(ToastMessage.class);
            client.registerClass(WinnerMessage.class);
            client.registerClass(LoserMessage.class);

            //Calls registerCallback() to initialize the Callback-function of the Kryo-Client
            registerCallback();
            //Client connects to server and checks for errors
            try {
                client.connect(hostname);
            } catch (IOException e) {
                Log.e("Fehler:", "Beim Verbinden mit dem Server");
            }
            this.verbunden = true;
        }
    }

    //Getter and Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Method for registering Callbacks from server
     */
    private void registerCallback() {
        //Call for register Callback method from the Kryo-Client
        client.registerCallback(nachrichtVomServer -> {
            //Instanceof is used to differentiate between the Message-classes
            if (nachrichtVomServer instanceof TextMessage) {
                //Base-Message is casted to child class
                TextMessage message = (TextMessage) nachrichtVomServer;
                //Log is updated for the Chat
                updateLog(message.toString());
            }

            if (nachrichtVomServer instanceof AskPlayerForTurn) {
                //Base-Message is casted to child class
                AskPlayerForTurn message = (AskPlayerForTurn) nachrichtVomServer;
                //Message is displayed by the Client
                Log.d("Server:", message.getText());

                // Spieler wird nach einem Zug gefragt
                game.askPlayerforTurn();
                Log.i("Clinet Turn: ","Round: "+message.getRound());
                game.setRound(message.getRound());

            }

            if(nachrichtVomServer instanceof ToastMessage){
                ToastMessage message = (ToastMessage) nachrichtVomServer;
                game.toast(message.getMsg());
            }


            if (nachrichtVomServer instanceof UpdatePlayersPosition) {
                //Base-Message is casted to child class
                UpdatePlayersPosition updatePlayersPosition = (UpdatePlayersPosition) nachrichtVomServer;
                //Client displays the current Players position
                Log.d("Server:", "PlayersPosition: " + updatePlayersPosition.getPlayerId() + " ToField: " + updatePlayersPosition.getToField()
                        + " LobbyID: " + updatePlayersPosition.getLobbyId());

            }



            if (nachrichtVomServer instanceof UpdatePlayersPosition) {

                Log.d("Client: ", "Die Position eines Spielers auf dder Map soll upgedatet werden");

                //Base-Message is casted to child class
                UpdatePlayersPosition msg = (UpdatePlayersPosition) nachrichtVomServer;
                // Updates the position of a player on the map
                updatePositionOfPlayerOnMap(msg.getPlayerId(), msg.getToField());
            }

            if (nachrichtVomServer instanceof UpdateTicketCount){
                UpdateTicketCount msg=(UpdateTicketCount)nachrichtVomServer;
                Log.d("TICKET","type:"+msg.getType()+" count:"+msg.getCount());
                updateTicketCount(msg.getType(),msg.getCount());
            }
            if(nachrichtVomServer instanceof TravellogMessage){
                TravellogMessage travellogMessage=(TravellogMessage)nachrichtVomServer;
                updateTravellog(travellogMessage.getTravelLog(),travellogMessage.getRound());
            }
            if(nachrichtVomServer instanceof WinnerMessage){
                game.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        game.setIntentWinner();
                    }
                });
            }
            if (nachrichtVomServer instanceof LoserMessage){
                game.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        game.setIntentLoser();
                    }
                });
            }


        });


    }

    /**
     * Method for sending a Textmessage to the server for the Chat
     *
     * @param text Chat-Message
     */
    public void sendMessagetoServer(String text) {
        //Message ist refactored with the players username
        TextMessage message = new TextMessage(username + ": " + text);
        //Message gets send to the server
        client.sendMessage(message);

    }

    /**
     * Method for sending a turnMessage to the Server
     *
     * @param message Turn-Message
     */
    public void sendTurn(TurnMessage message) {
        Log.d("Client: ", "Ein Zug wurde an den Server geschickt");

        //Message gets initialized
        TurnMessage msg = new TurnMessage(playerID, message.getToField(), 0, message.getCard());
        Log.d("Client","playerID "+playerID+" tofield "+message.getToField()+" Card;"+msg.getCard());
        //Message is sent to the Server
        client.sendMessage(msg);
    }

    /**
     * Method for sending a readyMessage
     */
    public void sendReady() {
        //Ready-Message is initialized
        ReadyMessage message = new ReadyMessage();
        //Message is sent to the server
        client.sendMessage(message);
    }

    /**
     * Method for sending a sendRole Message
     */
    public void sendRole() {
        Log.d("Client: ", "Die Rolle des Spielers wurde an den Server geschickt");

        //SendRoleMessage is initialized
        SendRoleMessage message = new SendRoleMessage();
        message.setText(role);
        message.setPlayerId(playerID);
        message.setName(getUsername());
        message.setLobbyId(getLobbyID());
        //Message is sent to the Server
        client.sendMessage(message);

    }

    /**
     * Method for sending the Username
     */
    public void sendUsername() {
        //Message is initialized
        UsernameMessage msg = new UsernameMessage(username,playerID);
        //Message is sent to the server
        client.sendMessage(msg);
    }

    /**
     * Method for updating the log for the Chat
     *
     * @param text Chat-Message
     */
    public void updateLog(String text) {

        while (log == null)
        {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Previous String is added to the prev variable
        String prev = log.getText().toString();
        //Current Chat-Message is added to the toAdd variable
        String toAdd = text;
        //The two Strings get concatenated
        String newlog = prev + toAdd + "\n";
        //The log gets refreshed
        log.setText(newlog);
    }


    public void updateTravellog(TravelLog log, int round){
        game.addTravellogEntry(log, round);
    }



    //Getter and Setter
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    /**
     * Method for updating the Player position on the map
     *
     * @param id      Player ID
     * @param toField toField
     */
    public void updatePositionOfPlayerOnMap(int id, int toField)
    {
        while(game == null)
        {
            Log.d("Client:", "Warte auf Instanz von gameActivity");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.d("Client:", "Spieler wird gezeichnet");
        game.drawPlayer(id,toField);
    }

    public void updateTicketCount(String type, int count){
        game.updateCount(type,count);
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getPlayerID() {
        return playerID;
    }
}
