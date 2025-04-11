package com.CampusConnect.model;
import java.sql.Timestamp;

public class Society {
    private int societyId;
    private String name;
    private String description;
    private String logoUrl;
    private Timestamp createdAt;

    // Constructors
    public Society() {}

    public Society(int societyId, String name, String description, String logoUrl, Timestamp createdAt) {
        this.societyId = societyId;
        this.name = name;
        this.description = description;
        this.logoUrl = logoUrl;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getSocietyId() {
        return societyId;
    }

    public void setSocietyId(int societyId) {
        this.societyId = societyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
