package com.CampusConnect.controller;
import com.CampusConnect.dao.UserDAO;
import com.CampusConnect.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to login page
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String errorMessage = null;

        // Validate input
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            errorMessage = "Username and password are required";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // Authenticate user
        User user = userDAO.getUserByUsername(username);

        if (user != null && password.equals(user.getPassword())) { // In production, use proper password hashing
            // Create session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Redirect based on role
            if ("ADMIN".equals(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            } else {
                response.sendRedirect(request.getContextPath() + "/home");
            }
        } else {
            errorMessage = "Invalid username or password";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
