package com.vpaveldm.entity;

public class LocalNetwork {
    private String name;
    private String cable;
    private int speed;
    private String standard;
    private int id;

    public LocalNetwork() {
    }

    public LocalNetwork(int id, String name, String cable, int speed, String standard) {
        this.name = name;
        this.cable = cable;
        this.speed = speed;
        this.standard = standard;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCable() {
        return cable;
    }

    public void setCable(String cable) {
        this.cable = cable;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
