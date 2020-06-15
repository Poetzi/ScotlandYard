package com.example.scotlandyard.viewLayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.scotlandyard.Client.Messages.TurnMessage;
import com.example.scotlandyard.R;
import com.example.scotlandyard.modelLayer.TravelLog;
import com.example.scotlandyard.presenterLayer.Presenter;
import com.google.android.material.navigation.NavigationView;

/**
 * Class for running the Game
 */
public class gameActivity extends AppCompatActivity {


    //mapView is assigned
    private mapView map;
    //playerView is assigned
    private playerView player;
    //Presenter is assigned
    private Presenter presenter = Presenter.getInstance();
    //DrawerLayout is assigned
    private DrawerLayout drawerLayout;
    private Menu menu;
    private int round;
    private int taxiTickets=11;
    private int busTickets=8;
    private int uBahnTickets=4;
    /**
     * onCreate Method to start up the Game
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //ContentView is set up
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        map = findViewById(R.id.mapView);
        player = findViewById(R.id.playerView);

        drawerLayout = findViewById(R.id.drawer_layout);
        //NavigationView is assigned
        NavigationView nav = findViewById(R.id.nav_view);


        //this Object is sent to the presenter
        presenter.setGame(this);
        //Players are added to the game
        player.addPlayers();

        //Needed for Drawer-Layout. Needs a string for checking if it is open or closed.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        //DrawerListener is added to the drawerLayout
        drawerLayout.addDrawerListener(toggle);
        //Layout is synchronized and checked if it's open
        toggle.syncState();
        //Manu gets initialized
        //Menu is assigned
        menu = nav.getMenu();
        //Send the Travel-log to the presenter
        presenter.setTravellogMenu(menu);

        updateCount("taxi",taxiTickets);
        updateCount("bus",busTickets);
        updateCount("ubahn",uBahnTickets);

    }

    /**
     * Method for drawing a player
     *
     * @param playerId Player ID
     * @param toField  toField
     */
    public void drawPlayer(int playerId, int toField) {
        //player gets drawn
        player.drawSinglePlayer(playerId, toField, map.getPoints(), round);
    }

    /**
     * Method for using a taxi
     */
    public void useTaxi() {
        //Touched point on the Map gets assigned to a variable
        int toField = map.touchedPoint.getField();


        //TurnMessage is created
        TurnMessage msg = new TurnMessage(0, toField, 0, "taxi");
        new Thread(() -> {
            // Nachricht wird an den Server geschickt
            presenter.sendTurn(msg);
        }).start();



    }

    public void reduceTicket(String type){

        switch (type) {
            case "taxi":
                setTaxiTickets(taxiTickets-1);
                updateCount(type,getTaxiTickets());
                break;
            case "bus":
                setBusTickets(busTickets-1);
                updateCount(type,getBusTickets());
                break;
            case "ubahn":
                setuBahnTickets(uBahnTickets-1);
                updateCount(type,getuBahnTickets());
                break;
        }
    }

    /**
     * Method for using the bus
     */
    public void useBus() {
        //Touched point on the Map gets assigned to a variable
        int toField = map.touchedPoint.getField();

        //TurnMessage is created
        TurnMessage msg = new TurnMessage(0, toField, 0, "bus");

        new Thread(() -> {
            // Nachricht wird an den Server geschickt
            presenter.sendTurn(msg);
        }).start();


    }

    /**
     * Method for using the Ubahn
     */
    public void useUbahn() {
        //Touched point on the Map gets assigned to a variable
        int toField = map.touchedPoint.getField();
        //TurnMessage is created
        TurnMessage msg = new TurnMessage(0, toField, 0, "ubahn");

        new Thread(() -> {
            // Nachricht wird an den Server geschickt
            presenter.sendTurn(msg);
        }).start();
    }

    public void toast(String toast){

        Thread thread = new Thread(){
            public void run(){
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
                    }
                });
            }
        };
        thread.start();
    }

    /**
     * Method to differentiate between the onClick-Events
     *
     * @param v View
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.taxi:
                useTaxi();
                break;
            case R.id.bus:
                useBus();
                break;
            case R.id.ubahn:
                useUbahn();
                break;
        }
    }


    /**
     * Method for resuming the game
     */
    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * Method for pausing the game
     */
    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * Method for backPressed
     */
    @Override
    public void onBackPressed() {
        //Check if drawerLayout is open
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    /**
     * Method for starting the app-chat
     *
     * @param view View
     */
    public void chat(View view) {
        new Thread(() -> {
            //Setting up the new view
            Intent intent = new Intent(this, Chat.class);
            //Change to the new view
            startActivity(intent);
        }).start();
    }


    /**
     * Method for asking Players for their turn
     */
    public void askPlayerforTurn() {
        CharSequence text = "Bitte einen Zug angeben";
        System.out.println(text);
    }

    /**
     * Method for updating the count
     *
     * @param type  Type
     * @param count Count
     */
    public void updateCount(String type, int count) {
        String c = String.valueOf(count);
        TextView v;
        //Type of Ticket is checked

        switch (type) {
            case "taxi":
                v = findViewById(R.id.txtview_taxi);
                if(presenter.getPlayerID() == 0){
                    v.setText("");
                }else {
                    v.setText(c);
                }
                if (count == 0) {
                    Button b = findViewById(R.id.taxi);
                    b.setBackgroundColor(0xff888888);
                    b.setClickable(false);
                }
                break;
            case "bus":
                v = findViewById(R.id.txtview_bus);
                if(presenter.getPlayerID() == 0){
                    v.setText("");
                }else {
                    v.setText(c);
                }                if (count == 0) {
                    Button b = findViewById(R.id.bus);
                    b.setBackgroundColor(0xff888888);
                    b.setClickable(false);
                }
                break;
            case "ubahn":
                v = findViewById(R.id.txtview_metro);
                if(presenter.getPlayerID() == 0){
                    v.setText("");
                }else {
                    v.setText(c);
                }                if (count == 0) {
                    Button b = findViewById(R.id.ubahn);
                    b.setBackgroundColor(0xff888888);
                    b.setClickable(false);
                }
                break;
        }
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void addTravellogEntry(TravelLog log, int round){
        runOnUiThread(() -> {
            
                menu.add(round, 0, 0, "Ticket: " + log.getTicket()+" Position: "+log.getPosition());

        });

    }

    public void setIntentLoser(){
        Intent intent = new Intent(this, LoserScreen.class);
        startActivity(intent);
    }

    public void setIntentWinner(){
        Intent intent = new Intent(this, VictoryScreen.class);
        startActivity(intent);
    }

    public int getTaxiTickets() {
        return taxiTickets;
    }

    public void setTaxiTickets(int taxiTickets) {
        this.taxiTickets = taxiTickets;
    }

    public int getBusTickets() {
        return busTickets;
    }

    public void setBusTickets(int busTickets) {
        this.busTickets = busTickets;
    }

    public int getuBahnTickets() {
        return uBahnTickets;
    }

    public void setuBahnTickets(int uBahnTickets) {
        this.uBahnTickets = uBahnTickets;
    }

}
