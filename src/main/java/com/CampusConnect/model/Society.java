package com.CampusConnect.model;
import java.sql.Timestamp;

public class Society {
    private int soId;
    private String name;
    private String description;
    private String logoUrl;

    // Getters and setters
    public int getSoId() { return soId; }
    public void setSoId(int soId) { this.soId = soId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
}