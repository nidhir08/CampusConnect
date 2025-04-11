package com.CampusConnect.dao;
import com.CampusConnect.util.DBConnection;
import com.CampusConnect.model.Event;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    public List<Event> getAllEvents() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Event> events = new ArrayList<>();

        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM events ORDER BY event_date DESC";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Event event = new Event();
                event.setEventId(rs.getInt("event_id"));
                event.setSocietyId(rs.getInt("society_id"));
                event.setTitle(rs.getString("title"));
                event.setDescription(rs.getString("description"));
                event.setEventDate(rs.getTimestamp("event_date"));
                event.setLocation(rs.getString("location"));
                event.setCreatedAt(rs.getTimestamp("created_at"));
                events.add(event);
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

        return events;
    }

    public List<Event> getEventsBySociety(int societyId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Event> events = new ArrayList<>();

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM events WHERE society_id = ? ORDER BY event_date DESC";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, societyId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Event event = new Event();
                event.setEventId(rs.getInt("event_id"));
                event.setSocietyId(rs.getInt("society_id"));
                event.setTitle(rs.getString("title"));
                event.setDescription(rs.getString("description"));
                event.setEventDate(rs.getTimestamp("event_date"));
                event.setLocation(rs.getString("location"));
                event.setCreatedAt(rs.getTimestamp("created_at"));
                events.add(event);
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

        return events;
    }

    public boolean addEvent(Event event) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;

        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO events (society_id, title, description, event_date, location) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, event.getSocietyId());
            stmt.setString(2, event.getTitle());
            stmt.setString(3, event.getDescription());
            stmt.setTimestamp(4, new Timestamp(event.getEventDate().getTime()));
            stmt.setString(5, event.getLocation());

            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;

            // If event is added successfully, create notifications for all users
            if (success) {
                createNotificationsForEvent(conn, stmt.getGeneratedKeys().getInt(1));
            }
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

    private void createNotificationsForEvent(Connection conn, int eventId) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Get all users
            String sql = "SELECT user_id FROM users";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Create a notification for each user
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                createNotification(conn, userId, eventId);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
    }

    private void createNotification(Connection conn, int userId, int eventId) throws SQLException {
        PreparedStatement stmt = null;

        try {
            String sql = "INSERT INTO notifications (user_id, event_id) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, eventId);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
        }
    }
}