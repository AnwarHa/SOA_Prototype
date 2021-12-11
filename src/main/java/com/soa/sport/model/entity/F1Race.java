package com.soa.sport.model.entity;

public class F1Race {

    private int season;
    private String raceName;
    private String date;

    public F1Race(int season, String raceName, String date) {
        this.season = season;
        this.raceName = raceName;
        this.date = date;
    }

    public F1Race() {
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "F1Race{" +
                "season='" + season + '\'' +
                ", raceName='" + raceName + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
