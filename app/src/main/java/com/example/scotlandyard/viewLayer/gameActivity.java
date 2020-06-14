package com.example.scotlandyard.viewLayer;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
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
    //TurnMessage is assigned
    private TurnMessage msg;
    //check is assigned
    private boolean check = true;
    //confirm is assigned
    private boolean confirm = false;
    //Sensor Manager is assigned
    private SensorManager sensorManager;
    //Sensor is assigned
    private Sensor accelerometer;
    //ShakeDetector is assigned
    private ShakeDetector shakeDetector;
    //TextView is assigned
    private TextView cheatTicketCount;
    //DrawerLayout is assigned
    private DrawerLayout drawerLayout;
    //NavigationView is assigned
    private NavigationView nav;
    //Menu is assigned
    private Menu menu;

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
        nav = findViewById(R.id.nav_view);


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
        menu = nav.getMenu();
        //Send the Travel-log to the presenter
        presenter.setTravellogMenu(menu);

    }

    /**
     * Method for drawing a player
     *
     * @param playerId Player ID
     * @param toField  toField
     */
    public void drawPlayer(int playerId, int toField) {
        //player gets drawn
        player.drawSinglePlayer(playerId, toField);
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
            case R.id.blackTicket:
                useBlackTicket();
                break;
            case R.id.doubleMove:
                Toast.makeText(getApplicationContext(), "Double Move Pressed", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void useBlackTicket() {
        //Touched point on the Map gets assigned to a variable
        int toField = map.touchedPoint.getField();
        //TurnMessage is created
        TurnMessage msg = new TurnMessage(0, toField, 0, "black");

        new Thread(() -> {
            // Nachricht wird an den Server geschickt
            presenter.sendTurn(msg);
        }).start();
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
        runOnUiThread(() -> {
            String c = String.valueOf(count);
            TextView v;
            //Type of Ticket is checked
            switch (type) {
                case "Taxi":
                    v = findViewById(R.id.txtview_taxi);
                    v.setText(c);
                    if (count == 0) {
                        Button b = findViewById(R.id.taxi);
                        b.setBackgroundColor(0xff888888);
                        b.setClickable(false);
                    }
                    break;
                case "Bus":
                    v = findViewById(R.id.txtview_bus);
                    v.setText(c);
                    if (count == 0) {
                        Button b = findViewById(R.id.bus);
                        b.setBackgroundColor(0xff888888);
                        b.setClickable(false);
                    }
                    break;
                case "U-Bahn":
                    v = findViewById(R.id.txtview_metro);
                    v.setText(c);
                    if (count == 0) {
                        Button b = findViewById(R.id.ubahn);
                        b.setBackgroundColor(0xff888888);
                        b.setClickable(false);
                    }
                    break;
                case "Black":
                    v = findViewById(R.id.txtview_black);
                    v.setText(c);
                    if (count == 0) {
                        Button b = findViewById(R.id.blackTicket);
                        b.setBackgroundColor(0xff888888);
                        b.setClickable(false);
                    }
                    break;
                case "DoubleMove":
                    v = findViewById(R.id.txtview_double);
                    v.setText(c);
                    if (count == 0) {
                        Button b = findViewById(R.id.doubleMove);
                        b.setBackgroundColor(0xff888888);
                        b.setClickable(false);
                    }
                    break;
                case "Cheat":
                    break;
            }
        });

    }

    //Getter and Setter
    public void setCheck(boolean check) {
        this.check = check;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public void addTravellogEntry(TravelLog log, int round){
        runOnUiThread(() -> {
            if (round==2 || round==7 || round==12 || round==17) {
                menu.add(round, 0, 0, log.getTicket()+", Position: "+log.getPosition());
            }else {
                menu.add(round, 0, 0, log.getTicket());
            }
        });

    }
}
