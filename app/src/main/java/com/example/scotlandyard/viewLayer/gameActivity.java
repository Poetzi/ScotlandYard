package com.example.scotlandyard.viewLayer;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.scotlandyard.R;
import com.example.scotlandyard.modelLayer.gameBoard.implementation.GameBoardImpl;
import com.example.scotlandyard.modelLayer.gameBoard.interfaces.GameBoard;
import com.example.scotlandyard.modelLayer.players.TravelLog;
import com.example.scotlandyard.modelLayer.transitions.implementation.TransitionImpl;
import com.example.scotlandyard.modelLayer.transitions.interfaces.Transition;
import com.google.android.material.navigation.NavigationView;

public class gameActivity extends AppCompatActivity {

    private Button taxi, bus, ubahn, blackTicket, doubleMove;
    private GameBoard gameBoard;
    private mapView map;
    private playerView player;

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
        map = (mapView) findViewById(R.id.mapView);
        player = findViewById(R.id.playerView);
        setUpFields();

        cheatBtn=findViewById(R.id.btn_cheat);
        cheatBtn.setVisibility(View.INVISIBLE);
        cheatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 *  Positionsänderung möglich
                 */
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

        //nur zum Testen von addTravelLog()
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                TravelLog travelLog=new TravelLog(1,"Taxi",false);
                addTravelLog(travelLog,4);
            }
        }, 10000);

    }


    public void setUpFields(){
        gameBoard = new GameBoardImpl();
        Transition tr = new TransitionImpl("bus",2,1);
        gameBoard.addFieldWithTransition(1,2,tr);

        tr = new TransitionImpl("ubahn",3,2);
        gameBoard.addFieldWithTransition(2,3,tr);

        tr = new TransitionImpl("taxi",1,3);
        gameBoard.addFieldWithTransition(3,1,tr);

    }


    public void onClick(View v){
        switch (v.getId()){
            case R.id.taxi:
                Toast.makeText(getApplicationContext(),"Taxi Pressed",Toast.LENGTH_SHORT).show();


                player.drawPlayer(map.touchedPoint.getX(), map.touchedPoint.getY());

                break;
            case R.id.bus:
                Toast.makeText(getApplicationContext(),"Bus Pressed",Toast.LENGTH_SHORT).show();
                player.drawPlayer(map.touchedPoint.getX(), map.touchedPoint.getY());
                break;
            case R.id.ubahn:
                Toast.makeText(getApplicationContext(),"U-Bahn Pressed",Toast.LENGTH_SHORT).show();
                player.drawPlayer(map.touchedPoint.getX(), map.touchedPoint.getY());
                break;
            case R.id.blackTicket:
                Toast.makeText(getApplicationContext(),"Black Ticket Pressed",Toast.LENGTH_SHORT).show();
                player.drawPlayer(map.touchedPoint.getX(), map.touchedPoint.getY());
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

    public void addTravelLog(TravelLog travelLog, int round) {
        String transport=travelLog.getTicket();
        menu.add(Menu.NONE, round, Menu.NONE, transport);

    }
}
