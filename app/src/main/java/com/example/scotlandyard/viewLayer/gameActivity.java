package com.example.scotlandyard.viewLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.scotlandyard.R;
import com.example.scotlandyard.modelLayer.gameBoard.interfaces.GameBoard;
import com.example.scotlandyard.modelLayer.transitions.implementation.TransitionImpl;
import com.example.scotlandyard.modelLayer.transitions.interfaces.Transition;

public class gameActivity extends AppCompatActivity {

    private Button taxi, bus, ubahn, blackTicket, doubleMove;
    private GameBoard gameBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        taxi = findViewById(R.id.taxi);
        bus = findViewById(R.id.bus);
        ubahn = findViewById(R.id.ubahn);
        blackTicket = findViewById(R.id.blackTicket);
        doubleMove = findViewById(R.id.doubleMove);
        setUpFields();
    }


    public void setUpFields(){
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
