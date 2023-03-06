package com.lab6.client.entities;

public class Coordinates {

    private Integer x; //Поле не может быть null
    private Double y; //Поле не может быть null

    public Integer getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Coordinates() {}

    public Coordinates(int x, double y) {
        this.x = x;
        this.y = y;
    }

}
