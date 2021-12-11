package com.soa.sport.model.entity;

public class Cyclist {

    private int id;
    private String first_name;
    private String last_name;
    private String team;
    private String nationality;
    private int age;
    private int height;
    private int weight;

    public Cyclist(int id, String first_name, String last_name, String team, String nationality, int age, int height, int weight) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.team = team;
        this.nationality = nationality;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public Cyclist(String first_name, String last_name, String team, String nationality, int age, int height, int weight) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.team = team;
        this.nationality = nationality;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public Cyclist() {
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "ProCyclist{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", team='" + team + '\'' +
                ", nationality='" + nationality + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
