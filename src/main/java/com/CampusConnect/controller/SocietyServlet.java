package com.CampusConnect.controller;

import com.CampusConnect.dao.SocietyDAO;
import com.CampusConnect.model.Society;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/societies")

public class SocietyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SocietyDAO dao = new SocietyDAO();
        List<Society> societies = dao.getAllSocieties();
        request.setAttribute("societies", societies);
        RequestDispatcher dispatcher = request.getRequestDispatcher("societies.jsp");
        dispatcher.forward(request, response);

        //  System.out.println("Fetched societies: " + societies.size());

    }
}