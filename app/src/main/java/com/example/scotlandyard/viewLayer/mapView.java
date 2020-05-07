package com.example.scotlandyard.viewLayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.scotlandyard.R;

public class mapView extends View {

    Bitmap small;
    Paint paint = new Paint();

    public mapView(Context context) {
        super(context);
        small = BitmapFactory.decodeResource(getResources(), R.drawable.smallmap);
    }

    //Needed for relative Layout
    public mapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context){
        small = BitmapFactory.decodeResource(getResources(), R.drawable.smallmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(small,0,0,null);
        Bitmap f1 = BitmapFactory.decodeResource(getResources(),R.drawable.field1);
        canvas.drawBitmap(f1,17,178,null);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(15f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        canvas.drawLine(22,181,76,375,paint);

    }

}
