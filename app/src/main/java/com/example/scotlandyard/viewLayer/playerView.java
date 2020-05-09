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
    Bitmap f1 = BitmapFactory.decodeResource(getResources(), R.drawable.f1);
    int x = 0;
    int y = 0;

    public playerView(Context context) {
        super(context);
    }

    public playerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(f1,x,y,null);
    }

    public void drawPlayer(int x, int y){
        invalidate();
    }
}
