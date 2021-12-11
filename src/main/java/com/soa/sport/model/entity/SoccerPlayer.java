package com.soa.sport.model.entity;


public class SoccerPlayer {

    private int id;
    private String first_name;
    private String last_name;
    private String team;
    private String position;
    private String dob;
    private int goals;
    private int assists;


    public SoccerPlayer(int id, String first_name, String last_name, String team, String position, String dob, int goals, int assists) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.team = team;
        this.position = position;
        this.dob = dob;
        this.goals = goals;
        this.assists = assists;
    }

    public SoccerPlayer(String first_name, String last_name, String team, String position,String dob, int goals, int assists) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.team = team;
        this.position = position;
        this.dob = dob;
        this.goals = goals;
        this.assists = assists;
    }

    public SoccerPlayer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    @Override
    public String toString() {
        return "SoccerPlayer{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", team='" + team + '\'' +
                ", position='" + position + '\'' +
                ", dob=" + dob +
                ", goals=" + goals +
                ", assists=" + assists +
                '}';
    }
}
