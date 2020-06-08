package com.example.scotlandyard.viewLayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.scotlandyard.R;

public class playerView extends View {
    Bitmap player = BitmapFactory.decodeResource(getResources(), R.drawable.player);
    int x = -100;
    int y = -100;
    int imgOffset = 20;


    public playerView(Context context) {
        super(context);
    }

    public playerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(player, x - imgOffset, y - imgOffset, null);
    }

    public void drawPlayer(int x, int y) {
        this.x = x;
        this.y = y;
        invalidate();
    }
}
