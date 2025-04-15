package com.CampusConnect.controller;

import com.CampusConnect.dao.EventDAO;
import com.CampusConnect.dao.SocietyDAO;
import com.CampusConnect.model.Event;
import com.CampusConnect.model.Society;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


@WebServlet("/")
public class HomeServlet extends HttpServlet {
private EventDAO eventDAO;
private SocietyDAO societyDAO;

@Override
public void init() {
    eventDAO = new EventDAO();
    societyDAO = new SocietyDAO();
}

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    List<Event> upcomingEvents = eventDAO.getAllEventsWithSocietyNames();

    List<Society> societies = societyDAO.getAllSocieties();

    System.out.println("Fetched events: " + upcomingEvents.size());
    for (Event e : upcomingEvents) {
        System.out.println(e.getTitle() + " on " + e.getEventDate() + " by " + e.getSocietyName());
    }

    request.setAttribute("upcomingEvents", upcomingEvents);
    request.setAttribute("societies", societies);

    request.getRequestDispatcher("home.jsp").forward(request, response);
}

}