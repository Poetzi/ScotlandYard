package com.example.scotlandyard.viewLayer;

import android.annotation.SuppressLint;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class mapView extends View {

    Bitmap small;
    ArrayList<Points> points = new ArrayList<>();
    int imgOffset = 10;
    int paintOffset = 5;
    Canvas player;
    int numb =200;
    int x = 0;
    int y = 0;
    Position touchedPoint = new Position();

    //Fields
    Bitmap f4 = BitmapFactory.decodeResource(getResources(),R.drawable.fieldsnew);

    public mapView(Context context) {
        super(context);
    }

    //Needed for relative Layout
    public mapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        player = canvas;

        //drawing lines
        printLines(canvas);


        //Map image dimensions
        float height=791;
        float width=1678;

        float density=getResources().getDisplayMetrics().density;
        //scale factor specifies how much the display is larger than the map image
        float widthScale=getResources().getDisplayMetrics().widthPixels/width;
        float heightScale=(getResources().getDisplayMetrics().heightPixels-55*density)/height;

        String delimiter = ";";
        InputStream inputStream = getResources().openRawResource(R.raw.points);
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        String[] tempArr;
        try {
            while (( line = buffreader.readLine()) != null) {
                tempArr = line.split(delimiter);
                canvas.drawBitmap(f4,((Integer.valueOf(tempArr[0])-imgOffset)*widthScale)-20,((Integer.valueOf(tempArr[1])-imgOffset)*heightScale)-40,null);
                points.add(new Points((int) (Integer.valueOf(tempArr[0])*widthScale), (int) (Integer.valueOf(tempArr[1])*heightScale),R.drawable.fieldsnew,"Field"+tempArr[2],Integer.valueOf(tempArr[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                buffreader.close();
                inputreader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

/*
        //drawing points
        canvas.drawBitmap(f4,251 - imgOffset,518 - imgOffset,null);
        canvas.drawBitmap(f4, 186 - imgOffset, 286 - imgOffset,null);
        canvas.drawBitmap(f4, 109 - imgOffset, 459 - imgOffset,null);
        canvas.drawBitmap(f4, 182 - imgOffset, 282 - imgOffset,null);
        canvas.drawBitmap(f4, 47 - imgOffset, 623 - imgOffset,null);
        canvas.drawBitmap(f4, 465 - imgOffset, 439 - imgOffset,null);
        canvas.drawBitmap(f4, 328 - imgOffset, 339 - imgOffset,null);
        canvas.drawBitmap(f4, 403 - imgOffset, 155 - imgOffset,null);
        canvas.drawBitmap(f4, 542 - imgOffset, 259 - imgOffset,null);
        canvas.drawBitmap(f4, 590 - imgOffset, 168 - imgOffset,null);
        canvas.drawBitmap(f4, 736 - imgOffset, 265- imgOffset,null);
        canvas.drawBitmap(f4, 171- imgOffset, 703 - imgOffset,null);
        canvas.drawBitmap(f4, 382 - imgOffset, 600 - imgOffset,null);
        canvas.drawBitmap(f4, 596 - imgOffset, 535 - imgOffset,null);
        canvas.drawBitmap(f4, 502 - imgOffset, 700 - imgOffset,null);
        canvas.drawBitmap(f4, 394 - imgOffset, 873 - imgOffset,null);
        canvas.drawBitmap(f4, 85 - imgOffset, 907 - imgOffset,null);
        canvas.drawBitmap(f4, 525 - imgOffset, 973 - imgOffset,null);
        canvas.drawBitmap(f4, 643 - imgOffset, 793 - imgOffset,null);
        canvas.drawBitmap(f4, 731 - imgOffset, 639 - imgOffset,null);
        canvas.drawBitmap(f4, 830 - imgOffset, 473 - imgOffset,null);
        canvas.drawBitmap(f4, 886 - imgOffset, 368 - imgOffset,null);
        canvas.drawBitmap(f4, 1072 - imgOffset, 312 - imgOffset,null);
        canvas.drawBitmap(f4, 991 - imgOffset, 433 - imgOffset,null);
        canvas.drawBitmap(f4, 695 - imgOffset, 374- imgOffset,null);
        canvas.drawBitmap(f4,  903 - imgOffset,  757 - imgOffset,null);
        canvas.drawBitmap(f4,  800 - imgOffset,  918 - imgOffset,null);
        canvas.drawBitmap(f4,  1023 - imgOffset,  836 - imgOffset,null);
        canvas.drawBitmap(f4, 1122 - imgOffset, 695 - imgOffset,null);
        canvas.drawBitmap(f4,  1195 - imgOffset,  582 - imgOffset,null);
        canvas.drawBitmap(f4,  1285 - imgOffset,  442 - imgOffset,null);
        canvas.drawBitmap(f4,  969 - imgOffset, 245 - imgOffset,null);
        canvas.drawBitmap(f4,  798 - imgOffset,  153 - imgOffset,null);
        canvas.drawBitmap(f4,  997 - imgOffset,  604 - imgOffset,null);
        canvas.drawBitmap(f4,  1426 - imgOffset,  477 - imgOffset,null);
        canvas.drawBitmap(f4,  1396 - imgOffset,  653 - imgOffset,null);
        canvas.drawBitmap(f4,  1268 - imgOffset,  815 - imgOffset,null);
        //canvas.drawBitmap(f4,  1362 - imgOffset,  847 - imgOffset,null);
        canvas.drawBitmap(f4,  1467 - imgOffset,  883 - imgOffset,null);
        canvas.drawBitmap(f4,  1667 - imgOffset,  933 - imgOffset,null);
        canvas.drawBitmap(f4,  1682 - imgOffset,  727 - imgOffset,null);
        canvas.drawBitmap(f4,  1699 - imgOffset,  505 - imgOffset,null);
        canvas.drawBitmap(f4,  1727 - imgOffset,  185 - imgOffset,null);
        canvas.drawBitmap(f4,  1504 - imgOffset,  149 - imgOffset,null);
        canvas.drawBitmap(f4, 1163 - imgOffset,  362 - imgOffset,null);
        canvas.drawBitmap(f4,  1077 - imgOffset,  494 - imgOffset,null);




        //adding points to the list
        points.add(new Points(251,518,R.drawable.fieldsnew,"Field1",1));
        points.add(new Points(186,286,R.drawable.fieldsnew,"Field2",2));
        points.add(new Points(109,459,R.drawable.fieldsnew,"Field3",3));
        points.add(new Points(182,282,R.drawable.fieldsnew,"Field4",4));
        points.add(new Points(47,623,R.drawable.fieldsnew,"Field5",5));
        points.add(new Points(465,439,R.drawable.fieldsnew,"Field6",6));
        points.add(new Points(328,339,R.drawable.fieldsnew,"Field7",7));
        points.add(new Points(403,155,R.drawable.fieldsnew,"Field8",8));
        points.add(new Points(542,259,R.drawable.fieldsnew,"Field9",9));
        points.add(new Points(590,168,R.drawable.fieldsnew,"Field10",10));
        points.add(new Points(736,265,R.drawable.fieldsnew,"Field11",11));
        points.add(new Points(171,703,R.drawable.fieldsnew,"Field12",12));
        points.add(new Points(382,600,R.drawable.fieldsnew,"Field13",13));
        points.add(new Points(596,535,R.drawable.fieldsnew,"Field14",14));
        points.add(new Points(502,700,R.drawable.fieldsnew,"Field15",15));
        points.add(new Points(394,873,R.drawable.fieldsnew,"Field16",16));
        points.add(new Points(85,907,R.drawable.fieldsnew,"Field17",17));
        points.add(new Points(525,973,R.drawable.fieldsnew,"Field18",18));
        points.add(new Points(643,793,R.drawable.fieldsnew,"Field19",19));
        points.add(new Points(731,639,R.drawable.fieldsnew,"Field20",20));
        points.add(new Points(830,473,R.drawable.fieldsnew,"Field21",21));
        points.add(new Points(886,368,R.drawable.fieldsnew,"Field22",22));
        points.add(new Points(1072,312,R.drawable.fieldsnew,"Field23",23));
        points.add(new Points(991,433,R.drawable.fieldsnew,"Field24",24));
        points.add(new Points(695,374,R.drawable.fieldsnew,"Field25",25));
        points.add(new Points(903,757,R.drawable.fieldsnew,"Field26",26));
        points.add(new Points(800,918,R.drawable.fieldsnew,"Field27",27));
        points.add(new Points(1023,836,R.drawable.fieldsnew,"Field28",28));
        points.add(new Points(1122,695,R.drawable.fieldsnew,"Field29",29));
        points.add(new Points(1195,582,R.drawable.fieldsnew,"Field30",30));
        points.add(new Points(1285,442,R.drawable.fieldsnew,"Field31",31));
        points.add(new Points(969,245,R.drawable.fieldsnew,"Field32",32));
        points.add(new Points(798,153,R.drawable.fieldsnew,"Field33",33));
        points.add(new Points(997,604,R.drawable.fieldsnew,"Field34",34));
        points.add(new Points(1426,477,R.drawable.fieldsnew,"Field35",35));
        points.add(new Points(1396,653,R.drawable.fieldsnew,"Field36",36));
        points.add(new Points(1268,815,R.drawable.fieldsnew,"Field37",37));
        points.add(new Points(1467,883,R.drawable.fieldsnew,"Field38",38));
        points.add(new Points(1667,933,R.drawable.fieldsnew,"Field39",39));
        points.add(new Points(1682,727,R.drawable.fieldsnew,"Field40",40));
        points.add(new Points(1699,505,R.drawable.fieldsnew,"Field41",41));
        points.add(new Points(1727,185,R.drawable.fieldsnew,"Field42",42));
        points.add(new Points(1504,149,R.drawable.fieldsnew,"Field43",43));
        points.add(new Points(1163,362,R.drawable.fieldsnew,"Field44",44));
        points.add(new Points(1077,494,R.drawable.fieldsnew,"Field45",45));
*/


    }

    //Getting coordinates from img
/*
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            int x = (int) event.getX();
            int y = (int) event.getY();


            Log.i("Coordinates", "X=" + x + "  Y=" + y);
        }
        return false;
    }*/

    private void printLines(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10f);
        paint.setStyle(Paint.Style.STROKE);

        //Plain
        paint.setAntiAlias(true);
        canvas.drawLine(186,286,328,339,paint);

        paint.setColor(Color.RED);
        canvas.drawLine(186+imgOffset,286+imgOffset,328+imgOffset,339+imgOffset,paint);

        //Intersecting
        paint.setColor(Color.RED);
        canvas.drawLine(186+imgOffset,286+imgOffset,328+imgOffset,339+imgOffset,paint);
        canvas.drawLine(465+imgOffset,439+imgOffset,328+imgOffset,339+imgOffset,paint);
        canvas.drawLine(465+imgOffset,439+imgOffset,328+imgOffset,339+imgOffset,paint);
        canvas.drawLine(465+imgOffset,439+imgOffset,596+imgOffset,535+imgOffset,paint);
        canvas.drawLine(731+imgOffset,639+imgOffset,596+imgOffset,535+imgOffset,paint);
        canvas.drawLine(731+imgOffset,639+imgOffset,903+imgOffset,757+imgOffset,paint);
        canvas.drawLine(1023+imgOffset,836+imgOffset,903+imgOffset,757+imgOffset,paint);



    }

    //Getting info if the box has been checked
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int x = (int) event.getX();
        int y = (int) event.getY();
        Log.i("Coordinates", "X=" + x + "  Y=" + y);

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
                        Log.i("Point ",name+" got touched "+" X="+points.get(i).getX()
                        +" Y="+points.get(i).getY());
                        return true;

                    }
                }

        }
        return false;
    }

}
