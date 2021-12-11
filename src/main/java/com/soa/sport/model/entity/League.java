package com.soa.sport.model.entity;

public class League {
    private String id, name, light, dark;


    public League(String id) {
        this.id = id;
    }

    public League(String id, String name, String light, String dark) {
        this.id = id;
        this.name = name;
        this.light = light;
        this.dark = dark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public String getDark() {
        return dark;
    }

    public void setDark(String dark) {
        this.dark = dark;
    }
}
