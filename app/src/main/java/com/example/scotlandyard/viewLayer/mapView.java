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
    int imgOffset;
    int paintOffset = 5;
    Canvas player;
    int numb =200;
    int x = 0;
    int y = 0;
    Position touchedPoint = new Position();

    //Fields
    Bitmap f;

    float height=791;
    float width=1678;

    float density=getResources().getDisplayMetrics().density;
    //scale factor specifies how much the display is larger than the map image
    float widthScale=getResources().getDisplayMetrics().widthPixels/width;
    float heightScale=(getResources().getDisplayMetrics().heightPixels-55*density)/height;


    public mapView(Context context) {
        super(context);
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

                int resID = getContext().getResources().getIdentifier("field" + tempArr[2], "drawable", getContext().getApplicationInfo().packageName);
                //Log.d("MAP", "resID:" + resID + " " + getContext().getApplicationInfo().packageName);
                if (resID != 0) {
                    f = BitmapFactory.decodeResource(getContext().getResources(), resID);
                } else {
                    resID=R.drawable.field1;
                    f = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.field1);
                }

                imgOffset=f.getWidth()/2;
                points.add(new Points((int) (Integer.valueOf(tempArr[0])*widthScale), (int) (Integer.valueOf(tempArr[1])*heightScale),resID,"Field"+tempArr[2],Integer.valueOf(tempArr[2])));
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
        Points.allPoints=points;
    }

    //Needed for relative Layout
    public mapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
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

                int resID = getContext().getResources().getIdentifier("field" + tempArr[2], "drawable", getContext().getApplicationInfo().packageName);
                //Log.d("MAP", "resID:" + resID + " " + getContext().getApplicationInfo().packageName);
                if (resID != 0) {
                    f = BitmapFactory.decodeResource(getContext().getResources(), resID);
                } else {
                    resID=R.drawable.field1;
                    f = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.field1);
                }

                imgOffset=f.getWidth()/2;
                points.add(new Points((int) (Integer.valueOf(tempArr[0])*widthScale), (int) (Integer.valueOf(tempArr[1])*heightScale),resID,"Field"+tempArr[2],Integer.valueOf(tempArr[2])));
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
        Points.allPoints=points;
    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        player = canvas;

        //draw lines
        printLines(canvas);
        //draw points
        for (Points p:points){
            f=BitmapFactory.decodeResource(getContext().getResources(),p.getImg());
            canvas.drawBitmap(f,p.getX()-(widthScale*imgOffset),p.getY()-(heightScale*imgOffset),null);
        }

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
        //stroke width in pixel
        paint.setStrokeWidth(4f*density);
        paint.setStyle(Paint.Style.STROKE);

        String delimiter = ";";
        InputStream inputStream = getResources().openRawResource(R.raw.lines);
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        String[] tempArr;

        try {

            while (( line = buffreader.readLine()) != null) {
                tempArr = line.split(delimiter);
                int x1=points.get(Integer.valueOf(tempArr[0])-1).getX();
                int y1=points.get(Integer.valueOf(tempArr[0])-1).getY();

                int x2=points.get(Integer.valueOf(tempArr[1])-1).getX();
                int y2=points.get(Integer.valueOf(tempArr[1])-1).getY();

                for (int i=2;i<tempArr.length;i++){
                    if(i==2){
                        paintOffset=0;
                    }
                    if (tempArr[i].equals("t")){
                        paint.setColor(Color.YELLOW);
                    }else if (tempArr[i].equals("b")){
                        paint.setColor(Color.BLUE);
                    }else {
                        paint.setColor(Color.RED);
                    }

                    canvas.drawLine(x1+paintOffset,y1+paintOffset,x2+paintOffset,y2+paintOffset,paint);
                    paintOffset=(int)(3*density);
                }

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
        //Plain
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

*/

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
