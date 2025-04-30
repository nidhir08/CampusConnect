package com.CampusConnect.controller.admin;

import com.CampusConnect.dao.EventDAO;
import com.CampusConnect.model.Event;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class EditEventServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Instantiate the DAO to interact with the database
    private EventDAO eventDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        eventDAO = new EventDAO(); // Initialize the EventDAO
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all events from the database
        List<Event> events = eventDAO.getAllEvents();

        // Set the events list as a request attribute
        request.setAttribute("events", events);

        // Forward to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("editEvents.jsp");
        dispatcher.forward(request, response);
    }
}
