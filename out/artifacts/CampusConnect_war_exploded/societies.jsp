<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.CampusConnect.model.Society" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Societies - College Societies</title>
  <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container">
  <jsp:include page="include/header.jsp" />

  <main class="main-content">
    <section class="page-header">
      <h2>All Societies</h2>
      <p>Explore the various societies and clubs at our college.</p>
    </section>

    <section class="societies-section">
      <div class="societies-grid">
        <%
          List<Society> societies = (List<Society>) request.getAttribute("societies");
          if (societies != null && !societies.isEmpty()) {
            for (Society society : societies) {
        %>
        <div class="society-card">
          <div class="society-logo">
            <img src="<%= society.getLogoUrl() != null ? society.getLogoUrl() : "images/default-society.jpg" %>" alt="<%= society.getName() %>">
          </div>
          <h3><%= society.getName() %></h3>
          <p><%= society.getDescription().length() > 100 ? society.getDescription().substring(0, 100) + "..." : society.getDescription() %></p>
          <a href="societies?action=view&id=<%= society.getSocietyId() %>" class="btn btn-small">View Details</a>
        </div>
        <%
          }
        } else {
        %>
        <p>No societies found.</p>
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