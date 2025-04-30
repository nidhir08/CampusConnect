package com.CampusConnect.controller.admin;

import com.CampusConnect.dao.SocietyDAO;
import com.CampusConnect.model.Society;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AddSocietyServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String logoUrl = request.getParameter("logoUrl"); // from the form

//        System.out.println("Received name: " + name);
//        System.out.println("Description: " + description);
//        System.out.println("Logo: " + logoUrl);



        Society society = new Society();
        society.setName(name);
        society.setDescription(description);
        society.setLogoUrl(logoUrl);

        SocietyDAO dao = new SocietyDAO();
        boolean success = dao.addSociety(society); // 🔹 Here's where we call it

        // System.out.println("Added? " + success);

        if (success) {
            response.sendRedirect("admin/adminDashboard.jsp");  // or anywhere you'd like
        } else {
            request.setAttribute("errorMessage", "Failed to add society.");
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
        }
    }
}