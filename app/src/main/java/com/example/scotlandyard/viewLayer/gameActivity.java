package com.example.scotlandyard.viewLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.scotlandyard.R;

public class gameActivity extends AppCompatActivity {

    public Button taxi, bus, ubahn, blackTicket, doubleMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        taxi = findViewById(R.id.taxi);
        bus = findViewById(R.id.bus);
        ubahn = findViewById(R.id.ubahn);
        blackTicket = findViewById(R.id.blackTicket);
        doubleMove = findViewById(R.id.doubleMove);
    }


    public void onClick(View v){
        switch (v.getId()){
            case R.id.taxi:
                Toast.makeText(getApplicationContext(),"Taxi Pressed",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bus:
                Toast.makeText(getApplicationContext(),"Bus Pressed",Toast.LENGTH_SHORT).show();
                break;
            case R.id.ubahn:
                Toast.makeText(getApplicationContext(),"U-Bahn Pressed",Toast.LENGTH_SHORT).show();
                break;
            case R.id.blackTicket:
                Toast.makeText(getApplicationContext(),"Black Ticket Pressed",Toast.LENGTH_SHORT).show();
                break;
            case R.id.doubleMove:
                Toast.makeText(getApplicationContext(),"Double Move Pressed",Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
