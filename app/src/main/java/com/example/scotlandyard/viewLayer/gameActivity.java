package com.example.scotlandyard.viewLayer;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scotlandyard.Client.Messages.TurnMessage;
import com.example.scotlandyard.R;
import com.example.scotlandyard.modelLayer.TravelLog;
import com.example.scotlandyard.presenterLayer.Presenter;
import com.google.android.material.navigation.NavigationView;


public class gameActivity extends AppCompatActivity {

    private Button taxi, bus, ubahn, blackTicket, doubleMove;
    private mapView map;
    private playerView player;
    private Points playerPostion;
    private Presenter presenter = Presenter.getInstance();
    private TurnMessage msg;

    public void setCheck(boolean check) {
        this.check = check;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    private boolean check = true;
    private boolean confirm = false;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private ShakeDetector shakeDetector;
    private Button cheatBtn;
    private TextView cheatTicketCount;

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        taxi = findViewById(R.id.taxi);
        bus = findViewById(R.id.bus);
        ubahn = findViewById(R.id.ubahn);
        blackTicket = findViewById(R.id.blackTicket);
        doubleMove = findViewById(R.id.doubleMove);
        map = findViewById(R.id.mapView);
        player = findViewById(R.id.playerView);
        presenter.setGame(this);
        player.addPlayers();

        cheatBtn = findViewById(R.id.btn_cheat);
        cheatBtn.setVisibility(View.INVISIBLE);
        cheatBtn.setOnClickListener(view -> {
            msg = new TurnMessage(presenter.getUser().getId(), 0, 0, "cheat");
            new Thread(() -> {
                // Nachricht wird an den Server geschickt
                presenter.sendTurn(msg);

            }).start();
        });

        cheatTicketCount=findViewById(R.id.txtview_cheat);
        cheatTicketCount.setVisibility(View.INVISIBLE);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        } else {
            throw new NullPointerException();
        }
        shakeDetector = new ShakeDetector();
        shakeDetector.setOnShakeListener(count -> {
            if (cheatBtn.getVisibility() == View.INVISIBLE) {
                cheatBtn.setVisibility(View.VISIBLE);
                cheatTicketCount.setVisibility(View.VISIBLE);
            } else {
                cheatBtn.setVisibility(View.INVISIBLE);
                cheatTicketCount.setVisibility(View.INVISIBLE);
            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView nav = findViewById(R.id.nav_view);

        Menu menu = nav.getMenu();
        presenter.setTravellogMenu(menu);

        new Thread(() -> {
            // Nachricht wird an den Server geschickt
            presenter.sendMessagetoServer("DONE");

        }).start();

    }

    public void drawPlayer(int playerId, int toField){
        player.drawSinglePlayer(playerId,toField,map.getPoints());
    }


    public void useTaxi() {
        int toField = map.touchedPoint.getField();

        TurnMessage msg = new TurnMessage(0, toField, 0, "taxi");
        Thread t = new Thread() {
            public void run() {
                presenter.sendTurn(msg);

            }
        };
        new Thread(() -> {
            // Nachricht wird an den Server geschickt
            presenter.sendTurn(msg);

        }).start();



    }

    public void useBus() {
        int toField = map.touchedPoint.getField();

        TurnMessage msg = new TurnMessage(0, toField, 0, "bus");
        Thread t = new Thread() {
            public void run() {
                presenter.sendTurn(msg);

            }
        };
        new Thread(() -> {
            // Nachricht wird an den Server geschickt
            presenter.sendTurn(msg);

        }).start();



    }

    public void useUbahn() {
        int toField = map.touchedPoint.getField();

        TurnMessage msg = new TurnMessage(0, toField, 0, "ubahn");
        Thread t = new Thread() {
            public void run() {
                presenter.sendTurn(msg);

            }
        };
        new Thread(() -> {
            // Nachricht wird an den Server geschickt
            presenter.sendTurn(msg);

        }).start();


    }


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
                Toast.makeText(getApplicationContext(), "Black Ticket Pressed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.doubleMove:
                Toast.makeText(getApplicationContext(), "Double Move Pressed", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(shakeDetector, accelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        sensorManager.unregisterListener(shakeDetector);
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    public void chat(View view) {
        new Thread(() -> {
            Intent intent = new Intent(this, Chat.class);
            startActivity(intent);
        }).start();
    }


    public void askPlayerforTurn()
    {
        Context context = getApplicationContext();
        CharSequence text = "Bitte einen Zug angeben";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void updateCount(String type, int count){
        String c=String.valueOf(count);
        TextView v;
        switch(type){
            case "Taxi":
                v=findViewById(R.id.txtview_taxi);
                v.setText(c);
                if (count==0){
                    Button b=findViewById(R.id.taxi);
                    b.setBackgroundColor(0xff888888);
                    b.setClickable(false);
                }
                break;
            case "Bus":
                v=findViewById(R.id.txtview_bus);
                v.setText(c);
                if (count==0){
                    Button b=findViewById(R.id.bus);
                    b.setBackgroundColor(0xff888888);
                    b.setClickable(false);
                }
                break;
            case "U-Bahn":
                v=findViewById(R.id.txtview_metro);
                v.setText(c);
                if (count==0){
                    Button b=findViewById(R.id.ubahn);
                    b.setBackgroundColor(0xff888888);
                    b.setClickable(false);
                }
                break;
            case "Black":
                v=findViewById(R.id.txtview_black);
                v.setText(c);
                if (count==0){
                    Button b=findViewById(R.id.blackTicket);
                    b.setBackgroundColor(0xff888888);
                    b.setClickable(false);
                }
                break;
            case "DoubleMove":
                v=findViewById(R.id.txtview_double);
                v.setText(c);
                if (count==0){
                    Button b=findViewById(R.id.doubleMove);
                    b.setBackgroundColor(0xff888888);
                    b.setClickable(false);
                }
                break;
            case "Cheat":
                cheatTicketCount.setText(c);
                if (count==0){
                    cheatBtn.setBackgroundColor(0xff888888);
                    cheatBtn.setClickable(false);
                }
                break;
        }
    }
}
