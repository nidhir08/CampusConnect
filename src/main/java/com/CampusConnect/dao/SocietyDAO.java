package com.CampusConnect.dao;


import com.CampusConnect.model.Society;
import com.CampusConnect.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.CampusConnect.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SocietyDAO {
    public static List<Society> getAllSocieties() {
        List<Society> societies = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM societies";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Society society = new Society();
                society.setSoId(rs.getInt("so_id"));
                society.setName(rs.getString("name"));
                society.setDescription(rs.getString("description"));
                society.setLogoUrl(rs.getString("logo_url"));
                societies.add(society);

                System.out.println("Fetched Society: " + society.getName());
            }
            System.out.println("📦 Total Societies Fetched: " + societies.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return societies;
    }

    public boolean addSociety(Society society) {
        String sql = "INSERT INTO societies (name, description, logo_url) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, society.getName());
            stmt.setString(2, society.getDescription());
            stmt.setString(3, society.getLogoUrl());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;



        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Society getSocietyById(int soId) {
        Society society = null;
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM societies WHERE so_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, soId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                society = new Society();
                society.setSoId(rs.getInt("so_id"));
                society.setName(rs.getString("name"));
                society.setDescription(rs.getString("description"));
                society.setLogoUrl(rs.getString("logo_url"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return society;
    }

    public boolean updateSociety(Society society) {
        boolean updated = false;
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE societies SET name = ?, description = ?, logo_url = ? WHERE so_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, society.getName());
            stmt.setString(2, society.getDescription());
            stmt.setString(3, society.getLogoUrl());
            stmt.setInt(4, society.getSoId());

            int rows = stmt.executeUpdate();
            updated = rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }








}