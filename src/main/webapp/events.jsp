<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.CampusConnect.model.Event" %>
<%@ page import="com.CampusConnect.model.Society" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Events - College Societies</title>
  <link rel="stylesheet" href="/CampusConnect/css/styles.css">
</head>
<body>
<div class="container">
  <jsp:include page="include/header.jsp" />

  <main class="main-content">
    <section class="page-header">
      <h2>All Events</h2>
      <p>Stay updated with the latest events from all societies.</p>

      <%
        if (request.isUserInRole("ADMIN")) {
      %>
      <div class="admin-actions">
        <a href="events?action=add" class="btn btn-primary">Add New Event</a>
      </div>
      <%
        }
      %>
    </section>

    <section class="events-section">
      <%
        if (request.getAttribute("successMessage") != null) {
      %>
      <div class="success-message">
        <%= request.getAttribute("successMessage") %>
      </div>
      <%
        }
      %>

      <div class="events-list">
        <%
          List<Event> events = (List<Event>) request.getAttribute("events");
          if (events != null && !events.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm");
            for (Event event : events) {
              // Get society name (you would need to implement this in your DAO)
              String societyName = "Society Name"; // Placeholder
        %>
        <div class="event-item">
          <div class="event-date">
            <%= dateFormat.format(event.getEventDate()) %>
          </div>
          <div class="event-details">
            <h3><%= event.getTitle() %></h3>
            <div class="event-society">
              <span>Organized by:</span> <%= societyName %>
            </div>
            <p><%= event.getDescription() %></p>
            <div class="event-location">
              <span class="location-icon">📍</span> <%= event.getLocation() %>
            </div>
          </div>
        </div>
        <%
          }
        } else {
        %>
        <p>No events found.</p>
        <%
          }
        %>
      </div>
    </section>
  </main>

  <jsp:include page="include/footer.jsp" />
</div>
</body>
</html>