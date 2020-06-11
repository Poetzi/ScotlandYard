package com.example.scotlandyard.viewLayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.scotlandyard.R;

import java.util.ArrayList;

public class playerView extends View {
    Bitmap player = BitmapFactory.decodeResource(getResources(), R.drawable.player);
    int x = -100;
    int y = -100;
    int imgOffset = 20;
    ArrayList<Players> players = new ArrayList<>();
    ArrayList<Points> points = Points.allPoints;

    public playerView(Context context) {
        super(context);
        points = Points.allPoints;
    }

    public playerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        points = Points.allPoints;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < players.size() ; i++) {
            if(players.get(i).getPlayerId() == 0){
                canvas.drawBitmap(player, players.get(i).getX() - imgOffset, players.get(i).getY() - imgOffset, null);
            }
            if(players.get(i).getPlayerId() == 1){
                canvas.drawBitmap(player, players.get(i).getX() - imgOffset+100, players.get(i).getY() - imgOffset, null);
            }if(players.get(i).getPlayerId() == 2){
                canvas.drawBitmap(player, players.get(i).getX() - imgOffset, players.get(i).getY() - imgOffset, null);
            }
            if(players.get(i).getPlayerId() == 3){
                canvas.drawBitmap(player, players.get(i).getX() - imgOffset+100, players.get(i).getY() - imgOffset, null);
            }if(players.get(i).getPlayerId() == 4){
                canvas.drawBitmap(player, players.get(i).getX() - imgOffset, players.get(i).getY() - imgOffset, null);
            }
            if(players.get(i).getPlayerId() == 5){
                canvas.drawBitmap(player, players.get(i).getX() - imgOffset+100, players.get(i).getY() - imgOffset, null);
            }
        }

    }

    public void addPlayers(){
        for (int i = 0; i < 6 ; i++) {
            players.add(new Players(0,0,i,0));
        }
    }

    public void drawSinglePlayer(int playerId, int toField){
        int x = 0,y = 0;
        for (int i = 0; i <points.size() ; i++) {
            if(toField == points.get(i).getField()){
                x = points.get(i).getX();
                y = points.get(i).getX();
                break;
            }
        }
        players.get(playerId).setX(x);
        players.get(playerId).setY(y);
        players.get(playerId).setPosition(toField);
        invalidate();
    }

}
