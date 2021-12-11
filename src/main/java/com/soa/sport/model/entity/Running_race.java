package com.soa.sport.model.entity;


import java.sql.Time;

public class Running_race {
    private int id;
    private String name;
    private String organizer;
    private String location;
    private int distance;
    private int registration_price;
    private String date;
    private String starting_hour;

    public Running_race(int id, String name, String organizer, String location, int distance, int registration_price, String date, String starting_hour) {
        this.id = id;
        this.name = name;
        this.organizer = organizer;
        this.location = location;
        this.distance = distance;
        this.registration_price = registration_price;
        this.date = date;
        this.starting_hour = starting_hour;
    }

    public Running_race(String name, String organizer, String location, int distance, int registration_price, String date, String starting_hour) {
        this.name = name;
        this.organizer = organizer;
        this.location = location;
        this.distance = distance;
        this.registration_price = registration_price;
        this.date = date;
        this.starting_hour = starting_hour;
    }

    public Running_race(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getRegistration_price() {
        return registration_price;
    }

    public void setRegistration_price(int registration_price) {
        this.registration_price = registration_price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStarting_hour() {
        return starting_hour;
    }

    public void setStarting_hour(String starting_hour) {
        this.starting_hour = starting_hour;
    }
}
