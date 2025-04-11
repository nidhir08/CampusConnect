<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.CampusConnect.model.User" %>
<%@ page import="com.CampusConnect.model.Event" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Home - College Societies</title>
  <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container">
  <jsp:include page="header.jsp" />

  <main class="main-content">
    <section class="hero-section">
      <div class="hero-content">
        <h2>Welcome to College Societies</h2>
        <p>Your one-stop platform for all college society information and events.</p>

        <%
          User user = (User) session.getAttribute("user");
          if (user == null) {
        %>
        <div class="hero-buttons">
          <a href="login" class="btn btn-primary">Login</a>
          <a href="register" class="btn btn-secondary">Register</a>
        </div>
        <%
          }
        %>
      </div>
    </section>

    <section class="featured-section">
      <h2>Upcoming Events</h2>

      <div class="events-grid">
        <%
          List<com.CampusConnect.model.Event> upcomingEvents = (List<com.CampusConnect.model.Event>) request.getAttribute("upcomingEvents");
          if (upcomingEvents != null && !upcomingEvents.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm");
            for (com.CampusConnect.model.Event event : upcomingEvents) {
        %>
        <div class="event-card">
          <div class="event-date">
            <%= dateFormat.format(event.getEventDate()) %>
          </div>
          <h3><%= event.getTitle() %></h3>
          <p><%= event.getDescription().length() > 100 ? event.getDescription().substring(0, 100) + "..." : event.getDescription() %></p>
          <div class="event-location">
            <span class="location-icon">📍</span> <%= event.getLocation() %>
          </div>
          <a href="events?action=view&id=<%= event.getEventId() %>" class="btn btn-small">View Details</a>
        </div>
        <%
          }
        } else {
        %>
        <p>No upcoming events at the moment.</p>
        <%
          }
        %>
      </div>

      <div class="view-all">
        <a href="events" class="btn btn-secondary">View All Events</a>
      </div>
    </section>

    <section class="featured-section">
      <h2>Featured Societies</h2>

      <div class="societies-grid">
        <!-- This would be populated from the database -->
        <div class="society-card">
          <div class="society-logo">
            <img src="images/programming-club.jpg" alt="Programming Club">
          </div>
          <h3>Programming Club</h3>
          <p>A club for programming enthusiasts to learn, collaborate, and build projects together.</p>
          <a href="societies?action=view&id=1" class="btn btn-small">View Details</a>
        </div>

        <div class="society-card">
          <div class="society-logo">
            <img src="images/drama-society.jpg" alt="Drama Society">
          </div>
          <h3>Drama Society</h3>
          <p>For students interested in theater, acting, and performance arts.</p>
          <a href="societies?action=view&id=2" class="btn btn-small">View Details</a>
        </div>

        <div class="society-card">
          <div class="society-logo">
            <img src="images/debate-club.jpg" alt="Debate Club">
          </div>
          <h3>Debate Club</h3>
          <p>Improve your public speaking and debating skills with like-minded peers.</p>
          <a href="societies?action=view&id=3" class="btn btn-small">View Details</a>
        </div>
      </div>

      <div class="view-all">
        <a href="societies" class="btn btn-secondary">View All Societies</a>
      </div>
    </section>
  </main>

  <jsp:include page="footer.jsp" />
</div>
</body>
</html>