package com.example.scotlandyard.viewLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.scotlandyard.Client.Messages.TurnMessage;
import com.example.scotlandyard.R;
import com.example.scotlandyard.modelLayer.gameBoard.implementation.GameBoardImpl;
import com.example.scotlandyard.modelLayer.gameBoard.interfaces.GameBoard;
import com.example.scotlandyard.modelLayer.players.implementation.DetectiveImpl;
import com.example.scotlandyard.modelLayer.players.interfaces.Detective;
import com.example.scotlandyard.modelLayer.transitions.implementation.TransitionImpl;
import com.example.scotlandyard.modelLayer.transitions.interfaces.Transition;
import com.example.scotlandyard.presenterLayer.Presenter;

public class gameActivity extends AppCompatActivity {

    private Button taxi, bus, ubahn, blackTicket, doubleMove;
    private GameBoard gameBoard = new GameBoardImpl();
    private mapView map;
    private playerView player;
    private Points playerPostion;
    private Presenter presenter = Presenter.getInstance();
    private TurnMessage msg;
    private User user = new User("test");
    public boolean check = true;
    public String confirm = "no";

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
    }


    public void setUpFields(){
        user.setId(0);
        presenter.setUser(user);
        presenter.setGame(this);

       /* gameBoard.addFieldWithTransition(1,2,"bus");
        gameBoard.addFieldWithTransition(2,1,"bus");


        gameBoard.addFieldWithTransition(2,3,"ubahn");
        gameBoard.addFieldWithTransition(3,2,"ubahn");


        gameBoard.addFieldWithTransition(3,1,"taxi");
        gameBoard.addFieldWithTransition(1,3,"taxi");
*/
        //Initial position of player
        playerPostion = new Points(186,286,0," ",1);
        //player.drawPlayer(186,286);
    }

    public void useTaxi(){
        int toField = map.touchedPoint.getField();

        TurnMessage msg = new TurnMessage(0,toField,0,"taxi");
        new Thread(() -> {
            // Nachricht wird an den Server geschickt
            presenter.sendTurn(msg);

        }).start();

        while (check){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        check = true;

        if(confirm.equalsIgnoreCase("yes")){
            playerPostion.setField(toField);
            player.drawPlayer(map.touchedPoint.getX(), map.touchedPoint.getY());
            Toast.makeText(getApplicationContext(),"YESSSS",Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(getApplicationContext(),"Illegal move",Toast.LENGTH_SHORT).show();
        }
    }

    public void useBus(){
        int positionOfPlayer = playerPostion.getField();
        int toField = map.touchedPoint.getField();

        TurnMessage msg = new TurnMessage(0,toField,0,"Bus");
        new Thread(() -> {
            // Nachricht wird an den Server geschickt
            presenter.sendTurn(msg);

        }).start();

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

        TurnMessage msg = new TurnMessage(0,toField,0,"uBahn");
        new Thread(() -> {
            // Nachricht wird an den Server geschickt
            presenter.sendTurn(msg);

        }).start();

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

}
