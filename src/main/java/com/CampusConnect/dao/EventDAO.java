package com.CampusConnect.dao;
import com.CampusConnect.util.DBConnection;
import com.CampusConnect.model.Event;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    public List<Event> getAllEventsWithSocietyNames() {
        List<Event> events = new ArrayList<>();

        String sql = "SELECT e.*, s.name AS society_name " +
                "FROM events e " +
                "JOIN societies s ON e.society_id = s.so_id " +
                "ORDER BY e.event_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Event event = new Event();
                event.setEventId(rs.getInt("event_id"));
                event.setSocietyId(rs.getInt("society_id"));
                event.setTitle(rs.getString("title"));
                event.setDescription(rs.getString("description"));
                event.setEventDate(rs.getTimestamp("event_date"));
                event.setLocation(rs.getString("location"));
                event.setSocietyName(rs.getString("society_name"));
                events.add(event);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }

    public List<Event> getEventsBySocietyId(int soId) {
        List<Event> events = new ArrayList<>();

        String sql = "SELECT * FROM events WHERE society_id = ? ORDER BY event_date ASC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, soId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Event event = new Event();
                event.setEventId(rs.getInt("event_id"));
                event.setSocietyId(rs.getInt("society_id"));
                event.setTitle(rs.getString("title"));
                event.setDescription(rs.getString("description"));
                event.setEventDate(rs.getTimestamp("event_date"));
                event.setLocation(rs.getString("location"));

                events.add(event);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return events;
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM events");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int eventId = rs.getInt("event_id");
                int societyId = rs.getInt("society_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String location = rs.getString("location");
                Timestamp eventDate = rs.getTimestamp("event_date");

                Event event = new Event();
                event.setEventId(eventId);
                event.setSocietyId(societyId);
                event.setTitle(title);
                event.setDescription(description);
                event.setLocation(location);
                event.setEventDate(eventDate);

                events.add(event);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public static boolean addEvent(Event event) {
        String query = "INSERT INTO events (title, description, event_date, society_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, event.getTitle());
            ps.setString(2, event.getDescription());
            ps.setDate(3, new java.sql.Date(event.getEventDate().getTime()));
            ps.setInt(4, event.getSocietyId());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public void updateEvent(Event event) {
        String sql = "UPDATE events SET society_id = ?, title = ?, description = ?, location = ?, event_date = ? WHERE event_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, event.getSocietyId());
            stmt.setString(2, event.getTitle());
            stmt.setString(3, event.getDescription());
            stmt.setString(4, event.getLocation());
            stmt.setTimestamp(5, new Timestamp(event.getEventDate().getTime()));
            stmt.setInt(6, event.getEventId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteEvent(int eventId) {
        String sql = "DELETE FROM events WHERE event_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, eventId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean insertEvent(Event event) {
        boolean inserted = false;
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO events (title, description, event_date, society_id) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, event.getTitle());
            ps.setString(2, event.getDescription());
            ps.setDate(3, new java.sql.Date(event.getEventDate().getTime()));
            ps.setInt(4, event.getSocietyId());

            int rows = ps.executeUpdate();
            inserted = rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inserted;
    }
}