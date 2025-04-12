package com.CampusConnect.dao;

import com.CampusConnect.model.Member;
import com.CampusConnect.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    public List<Member> getMembersBySocietyId(int societyId) {
        List<Member> members = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT user_id, name, position FROM members WHERE society_id = ?")) {

            stmt.setInt(1, societyId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Member member = new Member();
                member.setUserId(rs.getInt("user_id"));
                member.setName(rs.getString("name"));
                member.setPosition(rs.getString("position"));
                members.add(member);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return members;
    }
}
