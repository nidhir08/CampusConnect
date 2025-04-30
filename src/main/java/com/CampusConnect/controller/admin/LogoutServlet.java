package com.CampusConnect.controller.admin;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // don't create if not exists
        if (session != null) {
            session.invalidate(); // destroy session
        }
        response.sendRedirect("adminLogin.jsp"); // redirect to login
    }
}
