package com.example.scotlandyard.viewLayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.scotlandyard.R;
import com.example.scotlandyard.modelLayer.players.interfaces.Player;

import java.util.ArrayList;

public class mapView extends View {

    Bitmap small;
    ArrayList<Points> points = new ArrayList<>();
    int imgOffset = 26;
    Canvas player;
    int numb =200;
    int x = 0;
    int y = 0;
    Position touchedPoint = new Position();

    //Fields
    Bitmap f1 = BitmapFactory.decodeResource(getResources(),R.drawable.f1);
    Bitmap f2 = BitmapFactory.decodeResource(getResources(),R.drawable.f2);
    Bitmap f3 = BitmapFactory.decodeResource(getResources(),R.drawable.f3);

    public mapView(Context context) {
        super(context);
    }

    //Needed for relative Layout
    public mapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //player = new Canvas();
        // init(context);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        player = canvas;

        //drawing lines
        printLines(canvas);

        //drawing points
        canvas.drawBitmap(f1,635 - imgOffset,347- imgOffset,null);
        canvas.drawBitmap(f2,892- imgOffset,344- imgOffset,null);
        canvas.drawBitmap(f3,635- imgOffset,574- imgOffset,null);

        //numb+=50;
        //canvas.drawBitmap(f1,x - imgOffset,y- imgOffset,null);


        //adding points to the list
        points.add(new Points(635,347,R.drawable.f1,"Field1",1));
        points.add(new Points(892,344,R.drawable.f2,"Field2",2));
        points.add(new Points(635,574,R.drawable.f3,"Field3",3));


    }

    public void drawPlayer(int x, int y ){
        this.x = x;
        this.y = y;
        //Log.i("test","test");
        player.drawBitmap(f1,200 - imgOffset,200- imgOffset,null);
        invalidate();
        player.drawBitmap(f1,200 - imgOffset,200- imgOffset,null);

    }

    //Getting coordinates from img
/*
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            float x = event.getX();
            float y = event.getY();


            Log.i("Coordinates", "X=" + x + "  Y=" + y);
        }
        return false;
    }
*/
    private void printLines(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(15f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawLine(635,347,892,344,paint);

        paint.setColor(Color.YELLOW);
        canvas.drawLine(635,347,635,574,paint);

        paint.setColor(Color.RED);
        canvas.drawLine(635,574,892,344,paint);

    }

    //Getting info if the box has been checked
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                //Check if the x and y position of the touch is inside the bitmap
                for (int i = 0; i <points.size(); i++) {
                    if( x > points.get(i).getX() - imgOffset && x < points.get(i).getX() + imgOffset && y > points.get(i).getY()- imgOffset  && y <  points.get(i).getY() + imgOffset )
                    {
                        //Bitmap touched
                        String name = points.get(i).getStrField();
                        touchedPoint.setX(points.get(i).x);
                        touchedPoint.setY(points.get(i).y);
                        touchedPoint.setField(points.get(i).getField());
                        Log.i("Point ",name+" got touched");
                        return true;

                    }
                }

        }
        return false;
    }

}
