<%@ page import="java.util.*,com.CampusConnect.model.Event,com.CampusConnect.model.Society" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Edit Event</title>
  <link rel="stylesheet" href="../css/styles.css" />
</head>
<body>
<div class="form-container">
  <h2>Edit Event</h2>
  <% Event event = (Event) request.getAttribute("event"); %>
  <% if (event == null) { %>
  <p style='color:red;'>⚠️ Event not found.</p>
  <% } else { %>
  <!-- form goes here -->
  <% } %>



  <form action="../EditEventServlet" method="post">
    <input type="hidden" name="eventId" value="<%= ((Event)request.getAttribute("event")).getEventId() %>" />

    <input type="text" name="title" placeholder="Title"
           value="<%= ((Event)request.getAttribute("event")).getTitle() %>" required />

    <textarea name="description" placeholder="Description"><%= ((Event)request.getAttribute("event")).getDescription() %></textarea>

    <input type="date" name="eventDate"
           value="<%= ((Event)request.getAttribute("event")).getEventDate().toString() %>" required />

    <input type="text" name="location" placeholder="Location"
           value="<%= ((Event)request.getAttribute("event")).getLocation() %>" required />

    <select name="societyId" required>
      <%
        List<Society> societies = (List<Society>) request.getAttribute("societies");
        int selectedSocietyId = ((Event)request.getAttribute("event")).getSocietyId();
        for (Society s : societies) {
      %>
      <option value="<%= s.getSoId() %>" <%= (s.getSoId() == selectedSocietyId) ? "selected" : "" %>>
        <%= s.getName() %>
      </option>
      <% } %>
    </select>

    <input type="submit" value="Update Event" />
  </form>

  <% if (request.getAttribute("errorMessage") != null) { %>
  <p class="error"><%= request.getAttribute("errorMessage") %></p>
  <% } %>
</div>
</body>
</html>