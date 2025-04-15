<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Add New Event</title>
  <link rel="stylesheet" href="../css/styles.css" />
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f6f8;
      padding: 20px;
    }

    .form-container {
      background-color: #fff;
      max-width: 500px;
      margin: auto;
      padding: 30px;
      border-radius: 8px;
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    }

    .form-container h2 {
      text-align: center;
      margin-bottom: 25px;
    }

    input[type="text"],
    input[type="date"],
    input[type="number"],
    textarea {
      width: 100%;
      padding: 12px;
      margin: 10px 0 20px 0;
      border: 1px solid #ccc;
      border-radius: 6px;
      font-size: 14px;
    }

    textarea {
      resize: vertical;
      min-height: 80px;
    }

    input[type="submit"] {
      background-color: #007bff;
      color: white;
      padding: 12px;
      width: 100%;
      border: none;
      border-radius: 6px;
      font-size: 16px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    input[type="submit"]:hover {
      background-color: #0056b3;
    }

    .error {
      color: red;
      font-size: 14px;
      text-align: center;
    }
  </style>
</head>
<body>
<div class="form-container">
  <h2>Add New Event</h2>
  <form action="../AddEventServlet" method="post">
    <input type="text" name="title" placeholder="Event Title" required />
    <textarea name="description" placeholder="Event Description" required></textarea>
    <input type="text" name="location" placeholder="Location" required />
    <input type="date" name="eventDate" required />
    <input type="number" name="societyId" placeholder="Society ID" required />
    <input type="submit" value="Add Event" />
  </form>

  <% if (request.getAttribute("errorMessage") != null) { %>
  <p class="error"><%= request.getAttribute("errorMessage") %></p>
  <% } %>
</div>
</body>
</html>