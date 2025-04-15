<%@ page import="com.CampusConnect.model.Event" %>
<%@ page import="com.CampusConnect.model.Society" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Dashboard - Campus Connect</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="container">
  <%@ include file="/include/header.jsp" %>
  <main class="main-content">
    <section class="hero-section">
      <div class="hero-content">


        <h2>Welcome to Campus Connect</h2>
        <p>Your one-stop platform for all college society information and events.</p>

        <%--                <div class="hero-buttons">--%>
        <%--                    <a href="#" class="btn btn-primary">Login</a>--%>
        <%--                    <a href="#" class="btn btn-secondary">Register</a>--%>
        <%--                </div>--%>

      </div>
    </section>

    <section class="featured-section">
      <h2>Upcoming Events</h2>

      <div class="events-grid">
        <%
          List<Event> upcomingEvents = (List<Event>) request.getAttribute("upcomingEvents");

          if (upcomingEvents != null && !upcomingEvents.isEmpty()) {

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMM dd, yyyy HH:mm");
            for (Event event : upcomingEvents) {

        %>
        <div class="event-card">
          <div class="event-date">
            <p><%= sdf.format(event.getEventDate()) %></p>
          </div>
          <h3><%= event.getTitle() %></h3>
          <p>Organized By : <%= event.getSocietyName() %></p>
          <p><%= event.getDescription() %></p>
          <div class="event-location">
            <span class="location-icon">📍</span> <%= event.getLocation() %>
          </div>
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
        <a href="${pageContext.request.contextPath}/events.jsp" class="btn btn-secondary">View All Events</a>
      </div>
    </section>

    <section class="featured-section">
      <h2>Featured Societies</h2>

      <div class="societies-grid">
        <%
          List<Society> societies = (List<Society>) request.getAttribute("societies");

          if (societies != null && !societies.isEmpty()) {
            int count = 0;
            for (Society society : societies) {
              if (count >= 3) break;
              count++;
        %>
        <div class="society-card">
          <div class="society-logo">
            <img src="<%= society.getLogoUrl() != null ? society.getLogoUrl() : "images/default-logo.png" %>" alt="<%= society.getName() %>">
          </div>
          <h3><%= society.getName() %></h3>
          <p><%= society.getDescription() %></p>
          <a href="#" class="btn btn-small">View Details</a>
        </div>
        <%
          }
        } else {
        %>
        <p>No societies available.</p>
        <%
          }
        %>
      </div>


      <div class="view-all">
        <a href="${pageContext.request.contextPath}/societies" class="btn btn-secondary">View All Societies</a>
      </div>
    </section>
  </main>
  <%@ include file="/include/footer.jsp" %>
</div>
</body>

</html>
