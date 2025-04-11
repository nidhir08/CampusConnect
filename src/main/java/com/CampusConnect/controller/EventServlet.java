package com.CampusConnect.controller;

import com.CampusConnect.dao.EventDAO;
import com.CampusConnect.dao.SocietyDAO;
import com.CampusConnect.model.Event;
import com.CampusConnect.model.Society;
//import com.CampusConnect.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/events")
public class EventServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EventDAO eventDAO;
    private SocietyDAO societyDAO;

    @Override
    public void init() {
        eventDAO = new EventDAO();
        societyDAO = new SocietyDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            // List all events
            List<Event> events = eventDAO.getAllEvents();
            request.setAttribute("events", events);
            request.getRequestDispatcher("/events.jsp").forward(request, response);
        } else if (action.equals("view")) {
            // View a specific event
            String eventIdStr = request.getParameter("id");
            if (eventIdStr != null && !eventIdStr.trim().isEmpty()) {
                try {
                    int eventId = Integer.parseInt(eventIdStr);
                    // Implement getEventById in EventDAO
                    // Event event = eventDAO.getEventById(eventId);

                    // For now, redirect to events page
                    response.sendRedirect(request.getContextPath() + "/events");
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/events");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/events");
            }
        } else if (action.equals("add") && request.isUserInRole("ADMIN")) {
            // Show form to add a new event
            List<Society> societies = societyDAO.getAllSocieties();
            request.setAttribute("societies", societies);
            request.getRequestDispatcher("/add-event.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("add") && request.isUserInRole("ADMIN")) {
            // Add a new event
            String societyIdStr = request.getParameter("societyId");
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String eventDateStr = request.getParameter("eventDate");
            String location = request.getParameter("location");

            // Validate input
            if (societyIdStr == null || societyIdStr.trim().isEmpty() ||
                    title == null || title.trim().isEmpty() ||
                    description == null || description.trim().isEmpty() ||
                    eventDateStr == null || eventDateStr.trim().isEmpty() ||
                    location == null || location.trim().isEmpty()) {

                request.setAttribute("errorMessage", "All fields are required");
                List<Society> societies = societyDAO.getAllSocieties();
                request.setAttribute("societies", societies);
                request.getRequestDispatcher("/add-event.jsp").forward(request, response);
                return;
            }

            try {
                int societyId = Integer.parseInt(societyIdStr);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                Date eventDate = dateFormat.parse(eventDateStr);

                Event event = new Event();
                event.setSocietyId(societyId);
                event.setTitle(title);
                event.setDescription(description);
                event.setEventDate(eventDate);
                event.setLocation(location);

                boolean success = eventDAO.addEvent(event);

                if (success) {
                    // Redirect to events page with success message
                    request.setAttribute("successMessage", "Event added successfully!");
                    response.sendRedirect(request.getContextPath() + "/events");
                } else {
                    request.setAttribute("errorMessage", "Failed to add event. Please try again.");
                    List<Society> societies = societyDAO.getAllSocieties();
                    request.setAttribute("societies", societies);
                    request.getRequestDispatcher("/add-event.jsp").forward(request, response);
                }
            } catch (NumberFormatException | ParseException e) {
                request.setAttribute("errorMessage", "Invalid input. Please try again.");
                List<Society> societies = societyDAO.getAllSocieties();
                request.setAttribute("societies", societies);
                request.getRequestDispatcher("/add-event.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/events");
        }
    }
}
