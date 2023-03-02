package com.lab6.client.entities;

public class Studio {

    private String name; //Поле может быть null

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Studio() {}

    public Studio(String name) {
        this.name = name;
    }

}
