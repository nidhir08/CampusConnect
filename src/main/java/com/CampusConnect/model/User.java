package com.CampusConnect.model;

import java.sql.Timestamp;

public class User {
    private String fullName;
    private String email;
    private String username;
    private String password;
    private String role;
    private int userId;
    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; } // 🔥 ADD THIS
    public void setRole(String role) { this.role = role; }

}
