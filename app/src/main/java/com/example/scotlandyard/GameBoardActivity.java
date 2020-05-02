package com.example.scotlandyard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.scotlandyard.modelLayer.gameBoard.Field;
import com.example.scotlandyard.modelLayer.gameBoard.Fields;
import com.example.scotlandyard.modelLayer.gameBoard.interfaces.GameBoard;
import com.example.scotlandyard.modelLayer.players.implementation.DetectiveImpl;
import com.example.scotlandyard.modelLayer.players.interfaces.Detective;
import com.example.scotlandyard.modelLayer.players.interfaces.MrX;
import com.example.scotlandyard.modelLayer.players.interfaces.Player;
import com.example.scotlandyard.modelLayer.transitions.interfaces.Transition;

import java.util.ArrayList;

public class GameBoardActivity extends AppCompatActivity {

    private TextView taxiTickets;
    private TextView busTickets;
    private TextView metroTickets;
    private TextView blackTickets;
    private TextView doubleMoveTickets;

    private TextView rounds;
    private TextView postition;

    private RelativeLayout relativeLayout;

    private Player player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);

        taxiTickets=findViewById(R.id.taxiTickets);
        busTickets=findViewById(R.id.busTicket);
        metroTickets=findViewById(R.id.metroTicket);
        blackTickets=findViewById(R.id.blackTicket);
        blackTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(GameBoardActivity.this).create();
                LayoutInflater factory = LayoutInflater.from(GameBoardActivity.this);
                alertDialog.setTitle(R.string.ticket_dialog_title);
                final View image = factory.inflate(R.layout.black_ticket_dialog, null);
                alertDialog.setView(image);
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Black Ticket soll eingelöst werden
                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Nein", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alertDialog.show();

            }

        });
        doubleMoveTickets=findViewById(R.id.doubleMoveTicket);
        doubleMoveTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(GameBoardActivity.this).create();
                LayoutInflater factory = LayoutInflater.from(GameBoardActivity.this);
                alertDialog.setTitle(R.string.ticket_dialog_title);
                final View image = factory.inflate(R.layout.double_move_dialog, null);
                alertDialog.setView(image);
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       //Doppelzug
                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Nein", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alertDialog.show();

            }

        });

     //   player=new DetectiveImpl("test",1);


        /**
         * Black Ticket und Double Move Ticket sollen nur angezeigt werden, wenn Player Mr.X ist.
         * Ansonsten sollen sie ausgeblendet werden.
         */
        if (player instanceof Detective){
            blackTickets.setVisibility(View.GONE);
            doubleMoveTickets.setVisibility(View.GONE);
        }

        rounds=findViewById(R.id.round);
        postition=findViewById(R.id.currentPosition);

        relativeLayout=findViewById(R.id.rl_fields);
        setFields();
      //  updateTickets();

    }

    //Ticketanzahl soll jede Runde aktualisiert werden.
    public void updateTickets(){
        if (player instanceof Detective){
            taxiTickets.setText(String.valueOf(((Detective) player).getTaxiTickets()));
            busTickets.setText(String.valueOf(((Detective) player).getBusTickets()));
            metroTickets.setText(String.valueOf(((Detective) player).getUndergroundTickets()));
        }else if (player instanceof MrX){
            blackTickets.setText(String.valueOf(((MrX) player).getBlackTickets()));
            doubleMoveTickets.setText(String.valueOf(((MrX) player).getDoubleMoveTickets()));
        }
    }

    //erzeugt Spielfelder
    public void setFields(){
        for (Field f: Fields.getFIELDS()){
            Button b=new Button(GameBoardActivity.this); //ImageView oder TextView
            b.setText(String.valueOf(f.getFieldNumber()));
            b.setX(f.getX());
            b.setY(f.getY());
            relativeLayout.addView(b);
        }
    }
}
