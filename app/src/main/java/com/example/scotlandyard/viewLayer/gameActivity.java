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

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private ShakeDetector shakeDetector;
    private Button cheatBtn;

    private DrawerLayout drawerLayout;
    private NavigationView nav;
    private Menu menu;
    private View header;

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

        cheatBtn=findViewById(R.id.btn_cheat);
        cheatBtn.setVisibility(View.INVISIBLE);
        cheatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TurnMessage msg = new TurnMessage(presenter.getUser().getId(),0,0,"cheat");
                new Thread(() -> {
                    // Nachricht wird an den Server geschickt
                    presenter.sendTurn(msg);

                }).start();
            }
        });

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        shakeDetector = new ShakeDetector();
        shakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                if (cheatBtn.getVisibility()==View.INVISIBLE){
                    cheatBtn.setVisibility(View.VISIBLE);
                }else {
                    cheatBtn.setVisibility(View.INVISIBLE);
                }
            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav=findViewById(R.id.nav_view);
        header=nav.getHeaderView(0);
        header=findViewById(R.id.nav_header);
        menu=nav.getMenu();

        //Beispielwerte
        menu.add(Menu.NONE,1,Menu.NONE,"Bus");
        menu.add(Menu.NONE,2,Menu.NONE,"U-Bahn");
        menu.add(Menu.NONE,3,Menu.NONE,"Taxi");

        presenter.setTravellogMenu(menu);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                TravelLog travelLog=new TravelLog(1,"Taxi",false);
                presenter.updateTravellogMenu(travelLog,4);
            }
        }, 20000);


    }


    public void setUpFields(){
        user.setId(1);
        presenter.setUser(user);

        gameBoard.addFieldWithTransition(1,2,"bus");
        gameBoard.addFieldWithTransition(2,1,"bus");


        gameBoard.addFieldWithTransition(2,3,"ubahn");
        gameBoard.addFieldWithTransition(3,2,"ubahn");


        gameBoard.addFieldWithTransition(3,1,"taxi");
        gameBoard.addFieldWithTransition(1,3,"taxi");

        //Initial position of player
        playerPostion = new Points(635,347,0," ",1);
       // player.drawPlayer(635,347);
    }

    public void useTaxi(){
        int positionOfPlayer = playerPostion.getField();
        int toField = map.touchedPoint.getField();

        TurnMessage msg = new TurnMessage(0,toField,0,"taxi");
        new Thread(() -> {
            // Nachricht wird an den Server geschickt
            presenter.sendTurn(msg);

        }).start();

        if(gameBoard.movePlayer(positionOfPlayer,toField,"taxi")){
            playerPostion.setField(toField);
            player.drawPlayer(map.touchedPoint.getX(), map.touchedPoint.getY());
        }else {
            Toast.makeText(getApplicationContext(),"Illegal move",Toast.LENGTH_SHORT).show();
        }
    }

    public void useBus(){
        int positionOfPlayer = playerPostion.getField();
        int toField = map.touchedPoint.getField();


        if(gameBoard.movePlayer(positionOfPlayer,toField,"bus")){
            playerPostion.setField(toField);
            player.drawPlayer(map.touchedPoint.getX(), map.touchedPoint.getY());
        }else {
            Toast.makeText(getApplicationContext(),"Illegal move",Toast.LENGTH_SHORT).show();
        }
    }

    public void useUbahn(){
        int positionOfPlayer = playerPostion.getField();
        int toField = map.touchedPoint.getField();


        if(gameBoard.movePlayer(positionOfPlayer,toField,"ubahn")){
            playerPostion.setField(toField);
            player.drawPlayer(map.touchedPoint.getX(), map.touchedPoint.getY());
        }else {
            Toast.makeText(getApplicationContext(),"Illegal move",Toast.LENGTH_SHORT).show();
        }
    }


    public void onClick(View v){
        switch (v.getId()){
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
                Toast.makeText(getApplicationContext(),"Black Ticket Pressed",Toast.LENGTH_SHORT).show();
                break;
            case R.id.doubleMove:
                Toast.makeText(getApplicationContext(),"Double Move Pressed",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(shakeDetector, accelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        sensorManager.unregisterListener(shakeDetector);
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    public void chat(View view){
        new Thread(() -> {
            Intent intent = new Intent(this, Chat.class);
            startActivity(intent);
        }).start();
    }
}
