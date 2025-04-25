<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Campus Connect</title>
    <link rel="stylesheet" href="css/styles.css">
    <style>
        .auth-form {
            max-width: 400px;
            margin: 2rem auto;
            padding: 2rem;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        .form-group label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: 600;
        }

        .form-group input {
            width: 100%;
            padding: 0.75rem;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1rem;
        }

        .form-actions {
            margin-top: 2rem;
        }

        .error-message {
            background-color: #ffebee;
            color: #c62828;
            padding: 0.75rem;
            border-radius: 4px;
            margin-bottom: 1.5rem;
        }

        .success-message {
            background-color: #e8f5e9;
            color: #2e7d32;
            padding: 0.75rem;
            border-radius: 4px;
            margin-bottom: 1.5rem;
        }

        .auth-links {
            margin-top: 1.5rem;
            text-align: center;
        }

        .auth-links a {
            color: #3498db;
            text-decoration: none;
        }

        .auth-links a:hover {
            text-decoration: underline;
        }

        .role-indicator {
            text-align: center;
            margin-bottom: 1.5rem;
            padding: 0.5rem;
            border-radius: 4px;
            font-weight: 600;
        }

        .role-admin {
            background-color: #e3f2fd;
            color: #1565c0;
        }

        .role-student {
            background-color: #e8f5e9;
            color: #2e7d32;
        }
    </style>
</head>
<body>
<jsp:include page="include/header.jsp" />

<div class="container">
    <main class="main-content">
        <div class="auth-form">
            <h2>Login to Campus Connect</h2>

            <%
                String role = request.getParameter("role");
                if (role != null && !role.isEmpty()) {
                    String roleClass = role.equals("admin") ? "role-admin" : "role-student";
                    String roleDisplay = role.equals("admin") ? "Administrator" : "Student";
            %>
            <div class="role-indicator <%= roleClass %>">
                Login as <%= roleDisplay %>
            </div>
            <% } %>

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
                    <label for="username">Username or Email</label>
                    <input type="text" id="username" name="username" required>
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>

                <% if (role != null && !role.isEmpty()) { %>
                <input type="hidden" name="role" value="<%= role %>">
                <% } else { %>
                <div class="form-group">
                    <label for="role">Login As</label>
                    <select id="role" name="role" required>
                        <option value="">-- Select Role --</option>
                        <option value="student">Student</option>
                        <option value="admin">Administrator</option>
                    </select>
                </div>
                <% } %>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Login</button>
                </div>
            </form>

            <div class="auth-links">
                <p>Don't have an account? <a href="register">Register</a></p>
                <p><a href="forgot-password">Forgot Password?</a></p>
            </div>
        </div>
    </main>
</div>

<jsp:include page="include/footer.jsp" />
</body>
</html>