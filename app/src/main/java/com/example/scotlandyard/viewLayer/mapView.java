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

import java.util.ArrayList;

public class mapView extends View {

    Bitmap small;

    ArrayList<Points> points = new ArrayList<>();
    int imgOffset = 20;
    int paintOffset = 15;
    Canvas player;
    int numb =200;
    int x = 0;
    int y = 0;
    Position touchedPoint = new Position();

    //Fields
    Bitmap f4 = BitmapFactory.decodeResource(getResources(),R.drawable.fieldsnew);
    Bitmap f2 = BitmapFactory.decodeResource(getResources(),R.drawable.field2);
    Bitmap f1 = BitmapFactory.decodeResource(getResources(),R.drawable.field1);
    Bitmap f3 = BitmapFactory.decodeResource(getResources(),R.drawable.field3);
    Bitmap f5 = BitmapFactory.decodeResource(getResources(),R.drawable.field5);
    Bitmap f7 = BitmapFactory.decodeResource(getResources(),R.drawable.field7);
    Bitmap f13 = BitmapFactory.decodeResource(getResources(),R.drawable.field13);
    Bitmap f6 = BitmapFactory.decodeResource(getResources(),R.drawable.field6);
    Bitmap f15 = BitmapFactory.decodeResource(getResources(),R.drawable.field15);
    Bitmap f16 = BitmapFactory.decodeResource(getResources(),R.drawable.field16);
    Bitmap f18 = BitmapFactory.decodeResource(getResources(),R.drawable.field18);
    Bitmap f12 = BitmapFactory.decodeResource(getResources(),R.drawable.field12);
    Bitmap f8 = BitmapFactory.decodeResource(getResources(),R.drawable.field8);
    Bitmap f9 = BitmapFactory.decodeResource(getResources(),R.drawable.field9);
    Bitmap f10 = BitmapFactory.decodeResource(getResources(),R.drawable.field10);
    Bitmap f11 = BitmapFactory.decodeResource(getResources(),R.drawable.field11);
    Bitmap f25 = BitmapFactory.decodeResource(getResources(),R.drawable.field25);
    Bitmap f14 = BitmapFactory.decodeResource(getResources(),R.drawable.field14);
    Bitmap f20 = BitmapFactory.decodeResource(getResources(),R.drawable.field20);
    Bitmap f19 = BitmapFactory.decodeResource(getResources(),R.drawable.field19);
    Bitmap f27 = BitmapFactory.decodeResource(getResources(),R.drawable.field27);
    Bitmap f33 = BitmapFactory.decodeResource(getResources(),R.drawable.field33);
    Bitmap f32 = BitmapFactory.decodeResource(getResources(),R.drawable.field32);
    Bitmap f22 = BitmapFactory.decodeResource(getResources(),R.drawable.field22);
    Bitmap f21 = BitmapFactory.decodeResource(getResources(),R.drawable.field21);
    Bitmap f26 = BitmapFactory.decodeResource(getResources(),R.drawable.field26);
    Bitmap f28 = BitmapFactory.decodeResource(getResources(),R.drawable.field28);
    Bitmap f29 = BitmapFactory.decodeResource(getResources(),R.drawable.field29);
    Bitmap f34 = BitmapFactory.decodeResource(getResources(),R.drawable.field34);
    Bitmap f30 = BitmapFactory.decodeResource(getResources(),R.drawable.field30);
    Bitmap f45 = BitmapFactory.decodeResource(getResources(),R.drawable.field45);
    Bitmap f24 = BitmapFactory.decodeResource(getResources(),R.drawable.field24);
    Bitmap f23 = BitmapFactory.decodeResource(getResources(),R.drawable.field23);
    Bitmap f44 = BitmapFactory.decodeResource(getResources(),R.drawable.field44);
    Bitmap f31 = BitmapFactory.decodeResource(getResources(),R.drawable.field31);
    Bitmap f37 = BitmapFactory.decodeResource(getResources(),R.drawable.field37);
    Bitmap f38 = BitmapFactory.decodeResource(getResources(),R.drawable.field38);
    Bitmap f36 = BitmapFactory.decodeResource(getResources(),R.drawable.field36);
    Bitmap f35 = BitmapFactory.decodeResource(getResources(),R.drawable.field35);
    Bitmap f43 = BitmapFactory.decodeResource(getResources(),R.drawable.field43);
    Bitmap f42 = BitmapFactory.decodeResource(getResources(),R.drawable.field42);
    Bitmap f41 = BitmapFactory.decodeResource(getResources(),R.drawable.field41);
    Bitmap f40 = BitmapFactory.decodeResource(getResources(),R.drawable.field40);
    Bitmap f39 = BitmapFactory.decodeResource(getResources(),R.drawable.field39);




    public mapView(Context context) {
        super(context);
        //adding points to the list
        points.add(new Points(251,518,R.drawable.fieldsnew,"Field1",1));
        points.add(new Points(186,286,R.drawable.fieldsnew,"Field2",2));
        points.add(new Points(109,459,R.drawable.fieldsnew,"Field3",3));
        //points.add(new Points(182,282,R.drawable.fieldsnew,"Field4",4));
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
        // points.add(new Points(85,907,R.drawable.fieldsnew,"Field17",17));
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

        Points.allPoints = points;
    }

    //Needed for relative Layout
    public mapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //adding points to the list
        points.add(new Points(251,518,R.drawable.fieldsnew,"Field1",1));
        points.add(new Points(186,286,R.drawable.fieldsnew,"Field2",2));
        points.add(new Points(109,459,R.drawable.fieldsnew,"Field3",3));
        //points.add(new Points(182,282,R.drawable.fieldsnew,"Field4",4));
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
        // points.add(new Points(85,907,R.drawable.fieldsnew,"Field17",17));
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

        Points.allPoints = points;
    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        player = canvas;

        //drawing lines
        printLines(canvas);


        //drawing points
        canvas.drawBitmap(f1,251 - imgOffset,518 - imgOffset,null);
        canvas.drawBitmap(f2, 186 - imgOffset, 286 - imgOffset,null);
        canvas.drawBitmap(f3, 109 - imgOffset, 459 - imgOffset,null);
        //canvas.drawBitmap(f4, 182 - imgOffset, 282 - imgOffset,null);
        canvas.drawBitmap(f5, 47 - imgOffset, 623 - imgOffset,null);
        canvas.drawBitmap(f6, 465 - imgOffset, 439 - imgOffset,null);
        canvas.drawBitmap(f7, 328 - imgOffset, 339 - imgOffset,null);
        canvas.drawBitmap(f8, 403 - imgOffset, 155 - imgOffset,null);
        canvas.drawBitmap(f9, 542 - imgOffset, 259 - imgOffset,null);
        canvas.drawBitmap(f10, 590 - imgOffset, 168 - imgOffset,null);
        canvas.drawBitmap(f11, 736 - imgOffset, 265- imgOffset,null);
        canvas.drawBitmap(f12, 171- imgOffset, 703 - imgOffset,null);
        canvas.drawBitmap(f13, 382 - imgOffset, 600 - imgOffset,null);
        canvas.drawBitmap(f14, 596 - imgOffset, 535 - imgOffset,null);
        canvas.drawBitmap(f15, 502 - imgOffset, 700 - imgOffset,null);
        canvas.drawBitmap(f16, 394 - imgOffset, 873 - imgOffset,null);
       // canvas.drawBitmap(f4, 85 - imgOffset, 907 - imgOffset,null);
        canvas.drawBitmap(f18, 525 - imgOffset, 973 - imgOffset,null);
        canvas.drawBitmap(f19, 643 - imgOffset, 793 - imgOffset,null);
        canvas.drawBitmap(f20, 731 - imgOffset, 639 - imgOffset,null);
        canvas.drawBitmap(f21, 830 - imgOffset, 473 - imgOffset,null);
        canvas.drawBitmap(f22, 886 - imgOffset, 368 - imgOffset,null);
        canvas.drawBitmap(f23, 1072 - imgOffset, 312 - imgOffset,null);
        canvas.drawBitmap(f24, 991 - imgOffset, 433 - imgOffset,null);
        canvas.drawBitmap(f25, 695 - imgOffset, 374 - imgOffset,null);
        canvas.drawBitmap(f26,  903 - imgOffset,  757 - imgOffset,null);
        canvas.drawBitmap(f27,  800 - imgOffset,  918 - imgOffset,null);
        canvas.drawBitmap(f28,  1023 - imgOffset,  836 - imgOffset,null);
        canvas.drawBitmap(f29, 1122 - imgOffset, 695 - imgOffset,null);
        canvas.drawBitmap(f30,  1195 - imgOffset,  582 - imgOffset,null);
        canvas.drawBitmap(f31,  1285 - imgOffset,  442 - imgOffset,null);
        canvas.drawBitmap(f32,  969 - imgOffset, 245 - imgOffset,null);
        canvas.drawBitmap(f33,  798 - imgOffset,  153 - imgOffset,null);
        canvas.drawBitmap(f34,  997 - imgOffset,  604 - imgOffset,null);
        canvas.drawBitmap(f35,  1426 - imgOffset,  477 - imgOffset,null);
        canvas.drawBitmap(f36,  1396 - imgOffset,  653 - imgOffset,null);
        canvas.drawBitmap(f37,  1268 - imgOffset,  815 - imgOffset,null);
        //canvas.drawBitmap(f4,  1362 - imgOffset,  847 - imgOffset,null);
        canvas.drawBitmap(f38,  1467 - imgOffset,  883 - imgOffset,null);
        canvas.drawBitmap(f39,  1667 - imgOffset,  933 - imgOffset,null);
        canvas.drawBitmap(f40,  1682 - imgOffset,  727 - imgOffset,null);
        canvas.drawBitmap(f41,  1699 - imgOffset,  505 - imgOffset,null);
        canvas.drawBitmap(f42,  1727 - imgOffset,  185 - imgOffset,null);
        canvas.drawBitmap(f43,  1504 - imgOffset,  149 - imgOffset,null);
        canvas.drawBitmap(f44, 1163 - imgOffset,  362 - imgOffset,null);
        canvas.drawBitmap(f45,  1077 - imgOffset,  494 - imgOffset,null);

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
        paint.setStrokeWidth(13f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        //Plain
        paint.setColor(Color.BLUE);
        canvas.drawLine(186,286,328,339,paint);

        paint.setColor(Color.YELLOW);
        canvas.drawLine(394,873,502,700,paint);
        canvas.drawLine(186,286,109,459,paint);
        canvas.drawLine(47,623,109,459,paint);
        canvas.drawLine(47,623,171,703,paint);
        canvas.drawLine(251,518,109,459,paint);
        canvas.drawLine(251,518,382,600,paint);
        canvas.drawLine(465,439,382,600,paint);
        canvas.drawLine(382,600,502,700,paint);
        canvas.drawLine(394,873,525,973,paint);
        canvas.drawLine(596,535,502,700,paint);
        canvas.drawLine(1727,185,1504,149,paint);
        canvas.drawLine(1426,477,1504,149,paint);
        canvas.drawLine(465,439,542,259,paint);
        canvas.drawLine(643,793,800,918,paint);
        canvas.drawLine(903,757,800,918,paint);
        canvas.drawLine(596,535,695,374,paint);
        canvas.drawLine(403,155,542,259,paint);
        canvas.drawLine(695,374,542,259,paint);
        canvas.drawLine(695,374,830,473,paint);
        canvas.drawLine(643,793,731,639,paint);
        canvas.drawLine(736,265,590,168,paint);
        canvas.drawLine(736,265,886,368,paint);
        canvas.drawLine(736,265,798,153,paint);
        canvas.drawLine(1072,312,991,433,paint);
        canvas.drawLine(1072,312,969,245,paint);
        canvas.drawLine(1163,362,969,245,paint);
        canvas.drawLine(1163,362,1077,494,paint);
        canvas.drawLine(1163,362,1285,442,paint);
        canvas.drawLine(991,433,1077,494,paint);
        canvas.drawLine(997,604,903,757,paint);
        canvas.drawLine(1077,494,1195,582,paint);
        canvas.drawLine(1077,494,997,604,paint);
        canvas.drawLine(1122,695,997,604,paint);
        canvas.drawLine(1467,883,1361,838,paint);
        canvas.drawLine(1268,815,1361,838,paint);
        canvas.drawLine(1396,653,1361,838,paint);
        canvas.drawLine(1426,477,1699,505,paint);
        canvas.drawLine(1122,695,1195,582,paint);



        //Intersecting
        paint.setColor(Color.RED);
        canvas.drawLine(186+paintOffset+5,286+paintOffset+5,328+paintOffset+5,339+paintOffset+5,paint);
        canvas.drawLine(465+paintOffset,439+paintOffset,328+paintOffset,339+paintOffset,paint);
        canvas.drawLine(465+paintOffset,439+paintOffset,328+paintOffset,339+paintOffset,paint);
        canvas.drawLine(465+paintOffset,439+paintOffset,596+paintOffset,535+paintOffset,paint);
        canvas.drawLine(731+paintOffset,639+paintOffset,596+paintOffset,535+paintOffset,paint);
        canvas.drawLine(731+paintOffset,639+paintOffset,903+paintOffset,757+paintOffset,paint);
        canvas.drawLine(1023+paintOffset,836+paintOffset,903+paintOffset,757+paintOffset,paint);

        canvas.drawLine(969+paintOffset,245+paintOffset,886+paintOffset,368+paintOffset,paint);
        canvas.drawLine(830+paintOffset,473+paintOffset,886+paintOffset,368+paintOffset,paint);
        canvas.drawLine(830+paintOffset,473+paintOffset,731+paintOffset,639+paintOffset,paint);
        canvas.drawLine(643+paintOffset,793+paintOffset,731+paintOffset,639+paintOffset,paint);
        canvas.drawLine(643+paintOffset,793+paintOffset,525+paintOffset,973+paintOffset,paint);
        canvas.drawLine(1426,477,1396,653,paint);
        canvas.drawLine(1195,582,1396,653,paint);
        canvas.drawLine(1682,727,1396,653,paint);



        paint.setColor(Color.BLUE);
        canvas.drawLine(251+paintOffset+5,518+paintOffset+5,109+paintOffset+5,459+paintOffset+5,paint);
        canvas.drawLine(1426+paintOffset,477+paintOffset,1504+paintOffset,149+paintOffset,paint);
        canvas.drawLine(465+paintOffset,439+paintOffset,542+paintOffset,259+paintOffset,paint);
        canvas.drawLine(596+paintOffset,535+paintOffset,695+paintOffset,374+paintOffset,paint);
        canvas.drawLine(465+paintOffset,439+paintOffset,382+paintOffset,600+paintOffset,paint);



        //Single Lines
        paint.setColor(Color.BLUE);
        canvas.drawLine(403,155,328,339,paint);
        canvas.drawLine(251,518,328,339,paint);
        canvas.drawLine(251,518,171,703,paint);
        canvas.drawLine(394,873,171,703,paint);

        canvas.drawLine(969,245,886,368,paint);
        canvas.drawLine(830,473,886,368,paint);
        canvas.drawLine(830,473,731,639,paint);
        canvas.drawLine(643,793,525,973,paint);

        canvas.drawLine(643,793,502,700,paint);
        canvas.drawLine(1023,836,1122,695,paint);
        canvas.drawLine(1268,815,1122,695,paint);
        canvas.drawLine(542,259,590,168,paint);
        canvas.drawLine(736,265,695,374,paint);
        canvas.drawLine(969,245,798,153,paint);
        canvas.drawLine(886,368,991,433,paint);

        canvas.drawLine(1426,477,1285,442,paint);
        canvas.drawLine(997,604,830,473,paint);
        canvas.drawLine(1285,442,1195,582,paint);

        canvas.drawLine(1727,185,1699,505,paint);
        canvas.drawLine(1682,727,1699,505,paint);
        canvas.drawLine(1682,727,1667,933,paint);
        canvas.drawLine(1467,883,1667,933,paint);



    }

    //Getting info if the box has been checked
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int x = (int) event.getX();
        int y = (int) event.getY();
        //Log.i("Coordinates", "X=" + x + "  Y=" + y);

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

    public ArrayList<Points> getPoints(){
        return points;
    }

}
