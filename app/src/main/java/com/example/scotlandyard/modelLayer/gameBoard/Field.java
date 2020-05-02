package com.example.scotlandyard.modelLayer.gameBoard;

public class Field {
    private float x;
    private float y;
    private int fieldNumber;

    public Field(int fieldNumber, float x, float y){
        this.fieldNumber=fieldNumber;
        this.x=x;
        this.y=y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getFieldNumber() {
        return fieldNumber;
    }

    public void setFieldNumber(int fieldNumber) {
        this.fieldNumber = fieldNumber;
    }
}
