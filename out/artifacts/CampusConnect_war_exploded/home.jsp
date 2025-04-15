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
  <style>
    /* Popup Styles */
    .popup-overlay {
      display: none;
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0, 0, 0, 0.7);
      z-index: 1000;
      justify-content: center;
      align-items: center;
    }

    .popup-container {
      background-color: white;
      padding: 30px;
      border-radius: 8px;
      width: 90%;
      max-width: 500px;
      box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
      text-align: center;
      position: relative;
    }

    .popup-close {
      position: absolute;
      top: 10px;
      right: 15px;
      font-size: 24px;
      cursor: pointer;
      color: #999;
    }

    .popup-close:hover {
      color: #333;
    }

    .popup-title {
      font-size: 24px;
      margin-bottom: 20px;
      color: #3498db;
    }

    .popup-buttons {
      display: flex;
      justify-content: center;
      gap: 20px;
      margin-top: 30px;
    }

    .popup-button {
      padding: 12px 25px;
      border-radius: 4px;
      font-weight: 600;
      cursor: pointer;
      transition: all 0.3s;
      text-decoration: none;
    }

    .popup-button.admin {
      background-color: #3498db;
      color: white;
    }

    .popup-button.admin:hover {
      background-color: #2980b9;
    }

    .popup-button.student {
      background-color: #2ecc71;
      color: white;
    }

    .popup-button.student:hover {
      background-color: #27ae60;
    }

    .popup-register {
      margin-top: 20px;
      font-size: 14px;
    }

    .popup-register a {
      color: #3498db;
      text-decoration: underline;
    }
  </style>
</head>
<body>
<jsp:include page="include/header.jsp" />
<div class="container">


  <main class="main-content">
    <section class="hero-section">
      <div class="hero-content">
        <h2>Welcome to Campus Connect</h2>
        <p>Your one-stop platform for all college society information and events.</p>

        <%
          User user = (User) session.getAttribute("user");
          if (user == null) {
        %>
        <div class="hero-buttons">
<%--          <a href="login" class="btn btn-primary">Login</a>--%>
<%--          <a href="register" class="btn btn-secondary">Register</a>--%>
        </div>
        <%
          }
        %>
      </div>
    </section>
    <!-- Login/Register Modal -->
    <!-- Login Popup -->
    <div id="loginPopup" class="popup-overlay">
      <div class="popup-container">
        <% if (session.getAttribute("user") != null) { %>
        <span class="popup-close" onclick="closePopup()">&times;</span>
        <% } %>
        <h2 class="popup-title">Welcome to College Societies</h2>
        <p>Please login or register to continue exploring our platform.</p>

        <div class="popup-buttons">
          <a href="login?role=admin" class="popup-button admin">Login as Admin</a>
          <a href="login?role=student" class="popup-button student">Login as Student</a>
        </div>

        <p class="popup-register">
          Don't have an account? <a href="register">Register Now</a>
        </p>
      </div>
    </div>
    <section>

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
        <a href="events" class="btn btn-primary">View All Events</a>
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
        <a href="societies" class="btn btn-primary">View All Societies</a>
      </div>
    </section>
  </main>

</div>

<jsp:include page="include/footer.jsp" />


<script>
  // Check if user is logged in
  var userLoggedIn = <%= session.getAttribute("user") != null %>;

  // Show popup after 5 seconds if user is not logged in
  if (!userLoggedIn) {
    setTimeout(function() {
      document.getElementById('loginPopup').style.display = 'flex';
    }, 5000);
  }

  // Function to close the popup
  function closePopup() {
    document.getElementById('loginPopup').style.display = 'none';
  }

  // Prevent access to other pages without login
  document.addEventListener('DOMContentLoaded', function() {
    if (!userLoggedIn) {
      var links = document.querySelectorAll('a:not([href^="login"]):not([href^="register"]):not([href^="#"]):not(.popup-close)');

      links.forEach(function(link) {
        link.addEventListener('click', function(e) {
          if (!userLoggedIn) {
            e.preventDefault();
            document.getElementById('loginPopup').style.display = 'flex';
          }
        });
      });
    }
  });
</script>


</body>
</html>