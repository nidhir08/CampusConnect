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


public class EventServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EventDAO eventDAO = new EventDAO();
        List<Event> events = eventDAO.getAllEventsWithSocietyNames();

        request.setAttribute("events", events);
        request.getRequestDispatcher("/events.jsp").forward(request, response);
    }
}