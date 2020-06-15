package com.example.scotlandyard.viewLayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.scotlandyard.R;
import com.example.scotlandyard.presenterLayer.Presenter;

import java.util.ArrayList;

public class playerView extends View {
    Bitmap player = BitmapFactory.decodeResource(getResources(), R.drawable.detective);
    Bitmap Mrx = BitmapFactory.decodeResource(getResources(), R.drawable.mrx);
    private int round;
    Presenter presenter = Presenter.getInstance();

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
            if((players.get(i).getPlayerId() == 0) && (round == 0 || round == 3 || round == 7 || round ==12)){
                canvas.drawBitmap(Mrx, players.get(i).getX() - imgOffset, players.get(i).getY() - imgOffset, null);
            }else {
                canvas.drawBitmap(Mrx, -100, -100, null);
            }
            if(presenter.getPlayerID() == 0){
                canvas.drawBitmap(Mrx, players.get(i).getX() - imgOffset, players.get(i).getY() - imgOffset, null);
            }
            if(players.get(i).getPlayerId() == 1){
                canvas.drawBitmap(player, players.get(i).getX() - imgOffset, players.get(i).getY() - imgOffset, null);
            }
        }

    }

    public void addPlayers(){
        for (int i = 0; i < 2 ; i++) {
            players.add(new Players(0,0,i,0));
        }
    }

    public void drawSinglePlayer(int playerId, int toField, ArrayList<Points>points, int round){
        int x = 0,y = 0;
        this.round = round;
        for (int i = 0; i <points.size() ; i++) {
            if(toField == points.get(i).getField()){
                x = points.get(i).getX();
                y = points.get(i).getY();
                break;
            }
        }

        players.get(playerId).setX(x);
        players.get(playerId).setY(y);
        players.get(playerId).setPosition(toField);
        invalidate();
    }

}
