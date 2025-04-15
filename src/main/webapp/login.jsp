<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - College Societies</title>
    <link rel="stylesheet" href="/CampusConnect/css/styles.css">
</head>
<body>
<div class="container">
    <jsp:include page="include/header.jsp" />

    <main class="main-content">
        <div class="auth-form">
            <h2>Login</h2>

            <% if(request.getAttribute("errorMessage") != null) { %>
            <div class="error-message">
                <%= request.getAttribute("errorMessage") %>
            </div>
            <% } %>

            <% if(request.getAttribute("successMessage") != null) { %>
            <div class="success-message">
                <%= request.getAttribute("successMessage") %>
            </div>
            <% } %>

            <form action="login" method="post">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" required>
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Login</button>
                </div>
            </form>

            <div class="auth-links">
                <p>Don't have an account? <a href="register">Register</a></p>
            </div>
        </div>
    </main>

    <jsp:include page="include/footer.jsp" />
</div>
</body>
</html>