package com.CampusConnect.controller.admin;


import com.CampusConnect.dao.EventDAO;
import com.CampusConnect.model.Event;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class EventListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EventDAO eventDAO = new EventDAO();
        List<Event> events = eventDAO.getAllEventsWithSocietyNames();

        request.setAttribute("events", events);

        // Forward to the admin dashboard
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/adminDashboard.jsp");
        dispatcher.forward(request, response);
    }
}
