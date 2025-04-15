<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.CampusConnect.model.Society" %>
<%
  Society society = (Society) request.getAttribute("society");
%>
<!DOCTYPE html>
<html>
<head>
  <title>Edit Society</title>
</head>
<body>
<h2>Edit Society</h2>
<form method="post" action="<%= request.getContextPath() %>/EditSocietyServlet">
  <input type="hidden" name="so_id" value="<%= society.getSoId() %>">

  <label>Name:</label>
  <input type="text" name="name" value="<%= society.getName() %>" required><br>

  <label>Description:</label>
  <textarea name="description"><%= society.getDescription() %></textarea><br>

  <label>Logo URL:</label>
  <input type="text" name="logo_url" value="<%= society.getLogoUrl() %>"><br>

  <button type="submit">Update</button>
</form>
</body>
</html>