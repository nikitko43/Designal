package com.lawdev.designal.entities;

public class Performer {
    private int id;
    private String name;
    private String getWorkingOn;

    public Performer(int id, String name, String getWorkingOn) {
        this.id = id;
        this.name = name;
        this.getWorkingOn = getWorkingOn;
    }

    public String getName() {
        return name;
    }

    public String getWorkingOn() {
        return getWorkingOn;
    }

    public int getId() {
        return id;
    }
}
