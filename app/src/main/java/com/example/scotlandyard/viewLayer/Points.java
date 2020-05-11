package com.example.scotlandyard.viewLayer;

import com.example.scotlandyard.R;

public class Points {
    int x;
    int y;
    int img;
    int field;
    String strField;

    public static Points[] allPoints = {
            new Points(635,347, R.drawable.f1,"Field1",1),
            new Points(892,344,R.drawable.f2,"Field2",2),
            new Points(635,574,R.drawable.f3,"Field3",3)
    };

    public Points(int x, int y, int img, String strField, int field){
        this.x = x;
        this.y = y;
        this.img = img;
        this.strField = strField;
        this.field = field;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    public String getStrField() {
        return strField;
    }

    public void setStrField(String strField) {
        this.strField = strField;
    }

   public Points[] getAllPoints() {
        return allPoints;
    }

    public void setAllPoints(Points[] allPoints) {
        this.allPoints = allPoints;
    }
}
