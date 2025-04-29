<%--
  Created by IntelliJ IDEA.
  User: nidhirawat
  Date: 29/04/25
  Time: 11:37 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    // Invalidate the current session (logout)
    session.invalidate();

    // Redirect to homepage (update if your homepage is differently named)
    response.sendRedirect("home.jsp");
%>

