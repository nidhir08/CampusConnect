package com.CampusConnect.controller.admin;
import com.CampusConnect.dao.EventDAO;
import com.CampusConnect.model.Event;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddEventServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String eventDateStr = request.getParameter("eventDate");
            String location = request.getParameter("location");
            int societyId = Integer.parseInt(request.getParameter("societyId"));

            System.out.println("Received Location: " + location);

            // Convert String to java.util.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date utilDate = sdf.parse(eventDateStr);

            // Convert to java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            // Create event object
            Event event = new Event();
            event.setTitle(title);
            event.setDescription(description);
            event.setEventDate(sqlDate);
            event.setLocation(location);
            event.setSocietyId(societyId);

            boolean success = EventDAO.insertEvent(event);

            if (success) {
                response.sendRedirect("admin/adminDashboard.jsp");
                System.out.println("✅ Event added successfully to the database.");
            } else {
                request.setAttribute("error", "Failed to add event.");
                request.getRequestDispatcher("admin/addEvent.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Something went wrong.");
            request.getRequestDispatcher("admin/addEvent.jsp").forward(request, response);
        }
    }
}
