package com.CampusConnect.dao;


import com.CampusConnect.model.Society;
import com.CampusConnect.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SocietyDAO {

    public List<Society> getAllSocieties() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Society> societies = new ArrayList<>();

        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM societies ORDER BY name";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Society society = new Society();
                society.setSocietyId(rs.getInt("society_id"));
                society.setName(rs.getString("name"));
                society.setDescription(rs.getString("description"));
                society.setLogoUrl(rs.getString("logo_url"));
                society.setCreatedAt(rs.getTimestamp("created_at"));
                societies.add(society);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) DBConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return societies;
    }

    public Society getSocietyById(int societyId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Society society = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM societies WHERE society_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, societyId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                society = new Society();
                society.setSocietyId(rs.getInt("society_id"));
                society.setName(rs.getString("name"));
                society.setDescription(rs.getString("description"));
                society.setLogoUrl(rs.getString("logo_url"));
                society.setCreatedAt(rs.getTimestamp("created_at"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) DBConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return society;
    }

    public boolean addSociety(Society society) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;

        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO societies (name, description, logo_url) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, society.getName());
            stmt.setString(2, society.getDescription());
            stmt.setString(3, society.getLogoUrl());

            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) DBConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return success;
    }
}
