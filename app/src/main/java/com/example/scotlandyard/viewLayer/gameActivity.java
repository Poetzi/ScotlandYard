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
import android.widget.Toast;

import com.example.scotlandyard.Client.Messages.TurnMessage;
import com.example.scotlandyard.R;
import com.example.scotlandyard.modelLayer.gameBoard.implementation.GameBoardImpl;
import com.example.scotlandyard.modelLayer.gameBoard.interfaces.GameBoard;
import com.example.scotlandyard.modelLayer.players.TravelLog;
import com.example.scotlandyard.presenterLayer.Presenter;
import com.google.android.material.navigation.NavigationView;


public class gameActivity extends AppCompatActivity {

    private Button taxi, bus, ubahn, blackTicket, doubleMove;
    private GameBoard gameBoard = new GameBoardImpl();
    private mapView map;
    private playerView player;
    private Points playerPostion;
    private Presenter presenter = Presenter.getInstance();
    private TurnMessage msg;
    private User user = new User("test");

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
        setUpFields();

        cheatBtn = findViewById(R.id.btn_cheat);
        cheatBtn.setVisibility(View.INVISIBLE);
        cheatBtn.setOnClickListener(view -> {
            msg = new TurnMessage(presenter.getUser().getId(), 0, 0, "cheat");
            new Thread(() -> {
                // Nachricht wird an den Server geschickt
                presenter.sendTurn(msg);

            }).start();
        });

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
            } else {
                cheatBtn.setVisibility(View.INVISIBLE);
            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView nav = findViewById(R.id.nav_view);

        Menu menu = nav.getMenu();
        presenter.setTravellogMenu(menu);

        //Beispielwerte
        TravelLog travelLog;
        travelLog = new TravelLog(1, "Bus", false);
        presenter.updateTravellogMenu(travelLog, 1);

        travelLog = new TravelLog(2, "U-Bahn", false);
        presenter.updateTravellogMenu(travelLog, 2);

        travelLog = new TravelLog(3, "Taxi", false);
        presenter.updateTravellogMenu(travelLog, 3);


        Handler handler = new Handler();
        handler.postDelayed(() -> {
            TravelLog tl = new TravelLog(1, "Taxi", false);
            presenter.updateTravellogMenu(tl, 4);

            tl = new TravelLog(2, "Bus", false);
            tl.setCaughtCheating(true);
            presenter.updateTravellogMenu(tl, 5);
        }, 20000);


    }


    public void setUpFields() {
        user.setId(0);
        presenter.setUser(user);
        presenter.setGame(this);

        //Initial position of player
        playerPostion = new Points(100, 286, 0, " ", 1);
        player.drawPlayer(100, 286);
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



        while (check) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        setCheck(true);

        if (confirm) {
            playerPostion.setField(toField);
            player.drawPlayer(map.touchedPoint.getX(), map.touchedPoint.getY());
            Toast.makeText(getApplicationContext(), "YESSSS", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), "Illegal move", Toast.LENGTH_SHORT).show();
        }
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



        while (check) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        setCheck(true);

        if (confirm) {
            playerPostion.setField(toField);
            player.drawPlayer(map.touchedPoint.getX(), map.touchedPoint.getY());
            Toast.makeText(getApplicationContext(), "YESSSS", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Illegal move", Toast.LENGTH_SHORT).show();
        }
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



        while (check) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        setCheck(true);

        if (confirm) {
            playerPostion.setField(toField);
            player.drawPlayer(map.touchedPoint.getX(), map.touchedPoint.getY());
            Toast.makeText(getApplicationContext(), "YESSSS", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Illegal move", Toast.LENGTH_SHORT).show();
        }
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
}
