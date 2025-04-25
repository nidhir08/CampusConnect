<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.CampusConnect.model.User" %>
<head>
  <link rel="stylesheet" href="css/styles.css">
  <style>
    .logo h1 a {
      padding-left: 40px;
      display: inline-block;
      color: #333;
      font-size: 1.5rem;
      font-weight: bold;
    }
    .header-logo {
      width: 50px;
      height: auto;
      margin-right: 15px;
      vertical-align: middle;
    }
    .user-menu {
      position: relative;
      display: inline-block;
    }
    .user-menu-button {
      background-color: #3498db;
      color: white;
      border: none;
      border-radius: 20px;
      padding: 6px 15px;
      display: flex;
      align-items: center;
      cursor: pointer;
      font-weight: 500;
    }
    .user-menu-button:hover {
      background-color: #2980b9;
    }
    .user-avatar {
      width: 24px;
      height: 24px;
      border-radius: 50%;
      background-color: #fff;
      color: #3498db;
      display: flex;
      align-items: center;
      justify-content: center;
      margin-right: 8px;
      font-weight: bold;
    }
    .user-menu-content {
      display: none;
      position: absolute;
      right: 0;
      background-color: white;
      min-width: 180px;
      box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
      z-index: 1;
      border-radius: 4px;
      padding: 8px 0;
    }
    .user-menu-header {
      padding: 8px 16px;
      border-bottom: 1px solid #eee;
      margin-bottom: 8px;
    }
    .user-menu-header h4 {
      margin: 0;
      color: #333;
    }
    .user-menu-header p {
      margin: 4px 0 0;
      color: #666;
      font-size: 0.8rem;
    }
    .user-menu-content a {
      color: #333;
      padding: 8px 16px;
      text-decoration: none;
      display: block;
      font-size: 0.9rem;
    }
    .user-menu-content a:hover {
      background-color: #f1f1f1;
    }
    .user-menu:hover .user-menu-content {
      display: block;
    }
    .user-role-badge {
      font-size: 0.7rem;
      padding: 2px 6px;
      border-radius: 10px;
      margin-left: 5px;
      font-weight: bold;
    }
    .role-admin {
      background-color: #e3f2fd;
      color: #1565c0;
    }
    .role-student {
      background-color: #e8f5e9;
      color: #2e7d32;
    }
    .login-register {
      display: flex;
      gap: 10px;
    }
    .login-register a {
      padding: 6px 15px;
      border-radius: 4px;
      text-decoration: none;
      font-weight: 500;
    }
    .login-btn {
      background-color: #3498db;
      color: white;
    }
    .login-btn:hover {
      background-color: #2980b9;
    }
    .register-btn {
      background-color: #2ecc71;
      color: white;
    }
    .register-btn:hover {
      background-color: #27ae60;
    }
  </style>
</head>
<header class="header">
  <div class="logo">
    <img src="https://vips.edu/assets/logo-e78ff0ab.webp" class="header-logo">
  </div>
  <div class="logo">
    <h1><a href="${pageContext.request.contextPath}/home">Campus Connect</a></h1>
  </div>

  <nav class="main-nav">
    <ul>
      <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
      <li><a href="${pageContext.request.contextPath}/societies">Societies</a></li>
      <li><a href="${pageContext.request.contextPath}/events">Events</a></li>

      <%
        User user = (User) session.getAttribute("user");
        if (user != null) {
      %>
      <% if ("admin".equalsIgnoreCase(user.getRole())) { %>
      <li><a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a></li>
      <% } %>
      <li><a href="${pageContext.request.contextPath}/notifications">Notifications</a></li>
      <li class="user-menu">
        <div class="user-menu-button">
          <div class="user-avatar"><%= user.getFullName().substring(0, 1).toUpperCase() %></div>
          <%= user.getUsername() %>
        </div>
        <div class="user-menu-content">
          <div class="user-menu-header">
            <h4><%= user.getFullName() %></h4>
            <p><%= user.getEmail() %>
              <span class="user-role-badge <%= "admin".equalsIgnoreCase(user.getRole()) ? "role-admin" : "role-student" %>">
                  <%= user.getRole().toUpperCase() %>
                </span>
            </p>
          </div>
          <a href="${pageContext.request.contextPath}/profile">My Profile</a>
          <a href="${pageContext.request.contextPath}/my-societies">My Societies</a>
          <a href="${pageContext.request.contextPath}/my-events">My Events</a>
          <% if ("admin".equalsIgnoreCase(user.getRole())) { %>
          <a href="${pageContext.request.contextPath}/admin/dashboard">Admin Dashboard</a>
          <% } %>
          <a href="${pageContext.request.contextPath}/settings">Account Settings</a>
          <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>
      </li>
      <% } else { %>
      <li class="login-register">
        <a href="${pageContext.request.contextPath}/login" class="login-btn">Login</a>
        <a href="${pageContext.request.contextPath}/register" class="register-btn">Register</a>
      </li>
      <% } %>
    </ul>
  </nav>
</header>