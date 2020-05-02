package com.example.scotlandyard.modelLayer.gameBoard;

public class Fields {

    public Fields(){};

    private static final Field[] FIELDS={
            new Field(1,300,300),
            new Field(2,600,300),
            new Field(3,300,500),
            new Field(4,600, 500)
    };

    public static Field[] getFIELDS() {
        return FIELDS;
    }
}
