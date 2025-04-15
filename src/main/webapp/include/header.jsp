<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.CampusConnect.model.User" %>

<header class="header">
  <div class="logo">
    <h1><a href="${pageContext.request.contextPath}/home">College Societies</a></h1>
  </div>

  <nav class="main-nav">
    <ul>
      <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
      <li><a href="${pageContext.request.contextPath}/societies">Societies</a></li>
      <li><a href="${pageContext.request.contextPath}/events">Events</a></li>

<%--      <%--%>
<%--        User user = (User) session.getAttribute("user");--%>
<%--        if (user != null) {--%>
<%--          if ("ADMIN".equals(user.getRole())) {--%>
<%--      %>--%>
<%--      <li><a href="${pageContext.request.contextPath}/admin/dashboard">Admin</a></li>--%>
<%--      <%--%>
<%--        }--%>
<%--      %>--%>
<%--      <li><a href="${pageContext.request.contextPath}/notifications">Notifications</a></li>--%>
<%--      <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>--%>
<%--      <%--%>
<%--      } else {--%>
<%--      %>--%>
<%--      <li><a href="${pageContext.request.contextPath}/login">Login</a></li>--%>
<%--      <li><a href="${pageContext.request.contextPath}/register">Register</a></li>--%>
<%--      <%--%>
<%--        }--%>
<%--      %>--%>
    </ul>
  </nav>
</header>