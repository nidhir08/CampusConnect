package com.CampusConnect.controller;
import com.CampusConnect.model.User;
import com.CampusConnect.util.DBConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Simply forward to the login page
        // We don't need to set role as an attribute since we're reading it as a parameter in login.jsp
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Basic validation
        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Username and password are required.");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Get database connection
            conn = DBConnection.getConnection();

            // Check if connection is successful
            if (conn == null) {
                throw new SQLException("Database connection failed");
            }

            // Updated query to include role check
            String query = "SELECT * FROM users WHERE (username = ? OR email = ?) AND password = ?";

            // Check if role was provided and include it in the query if it was
            if (role != null && !role.isEmpty()) {
                query += " AND role = ?";
            }

            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, username);
            ps.setString(3, password);

            if (role != null && !role.isEmpty()) {
                ps.setString(4, role);
            }

            System.out.println("Executing query: " + query);
            System.out.println("With parameters: username/email=" + username + ", role=" + role);

            rs = ps.executeQuery();

            if (rs.next()) {
                // Login successful, create a User object and store in session
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));

                // Store the user object in session
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                System.out.println("User logged in successfully: " + user.getUsername() + " with role: " + user.getRole());

                // Redirect to home page
                response.sendRedirect("home");
            } else {
                // Login failed
                System.out.println("Login failed for user: " + username);
                request.setAttribute("errorMessage", "Invalid username/email or password.");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Show more specific error message
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) DBConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}