package com.CampusConnect.dao;


import com.CampusConnect.model.Society;
import com.CampusConnect.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class SocietyDAO {

    public List<Society> getAllSocieties() {
        List<Society> societies = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM societies";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Society society = new Society();
                society.setSoId(rs.getInt("so_id")); // use actual DB column name
                society.setName(rs.getString("name"));
                society.setDescription(rs.getString("description"));
                society.setLogoUrl(rs.getString("logo_url"));
                societies.add(society);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return societies;
    }
}
