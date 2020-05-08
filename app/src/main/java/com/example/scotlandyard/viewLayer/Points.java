package com.example.scotlandyard.viewLayer;

public class Points {
    int x;
    int y;
    int img;



    String field;

    public Points(int x, int y, int img, String field){
        this.x = x;
        this.y = y;
        this.img = img;
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
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
