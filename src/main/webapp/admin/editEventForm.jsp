<%@ page import="com.CampusConnect.model.Event" %>
<%
    Event event = (Event) request.getAttribute("event");
%>
<h2>Edit Event</h2>
<form action="UpdateEventServlet" method="post">
    <input type="hidden" name="id" value="">

    <label>Title:</label>
    <input type="text" name="title" value="<%= event.getTitle() %>" required><br>

    <label>Description:</label>
    <textarea name="description"><%= event.getDescription() %></textarea><br>

    <label>Date:</label>
    <input type="date" name="eventDate" value="<%= event.getEventDate().toString() %>" required><br>

    <input type="submit" value="Update Event">
</form>