package com.lzt.web.bean;

public class User {
    private int id;
    private String date;
    private double money;
    private String person;

    public User() {
    }

    public User(int id, String date, double money, String person) {

        this.id = id;
        this.date = date;
        this.money = money;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
