package com.CampusConnect.model;
import java.sql.Timestamp;
import java.util.Date;
public class Event {
    private int eventId;
    private int societyId;
    private String title;
    private String description;
    private Date eventDate;
    private String location;
    private String societyName; // fetched from JOIN

    // ✅ Default constructor
    public Event() {}

    // ✅ Parameterized constructor for easier object creation
    public Event(int eventId, int societyId, String title, String description, String location, Date eventDate) {
        this.eventId = eventId;
        this.societyId = societyId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.eventDate = eventDate;
    }

    // ✅ Getters and Setters
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getSocietyId() {
        return societyId;
    }

    public void setSocietyId(int societyId) {
        this.societyId = societyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }
}