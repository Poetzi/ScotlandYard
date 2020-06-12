package com.example.scotlandyard.viewLayer;

import java.util.ArrayList;

public class Points {
    int x;
    int y;
    int img;
    int field;
    String strField;
    static ArrayList<Points> allPoints;


    public Points(int x, int y, int img, String strField, int field) {
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

}
