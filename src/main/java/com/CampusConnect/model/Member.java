package com.CampusConnect.model;

public class Member {
    private int userId;
    private String name;
    private String position;

    public Member() {
    }

    public Member(int userId, String name, String position) {
        this.userId = userId;
        this.name = name;
        this.position = position;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}