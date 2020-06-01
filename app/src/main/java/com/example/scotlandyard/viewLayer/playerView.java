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
    Bitmap team=BitmapFactory.decodeResource(getResources(), R.drawable.player); //other image needed
    ArrayList<int[]> otherPlayers=new ArrayList<>();
    int x = -100;
    int y = -100;
    int imgOffset = player.getWidth()/2;


    public playerView(Context context) {
        super(context);
    }

    public playerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(player,x - imgOffset,y- imgOffset,null);
        for (int[]player:otherPlayers){
            canvas.drawBitmap(team,player[1]-imgOffset,player[2]-imgOffset,null);
        }
    }

    public void drawPlayer(int x, int y){
        this.x = x;
        this.y = y;
        invalidate();
    }

    public void updateOtherPlayersPosition(int playerId, int x, int y){
        if (otherPlayers.size()==0) {
            int[] value = {playerId, x, y};
            otherPlayers.add(value);
        }
        boolean foundPlayer=false;
        //Koordinaten von Spieler werden erneuert.
        for (int[] player:otherPlayers){
            if (player[0]==playerId){
                player[1]=x;
                player[2]=y;
                foundPlayer=true;
            }
        }
        //falls Spieler noch nicht in Liste ist.
        if (!foundPlayer){
            int[] value = {playerId, x, y};
            otherPlayers.add(value);
        }

        invalidate();
    }
}
