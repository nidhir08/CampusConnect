package com.CampusConnect.controller;

import com.CampusConnect.dao.SocietyDAO;
import com.CampusConnect.model.Society;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/societies")
public class SocietyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SocietyDAO societyDAO;

    @Override
    public void init() {
        societyDAO = new SocietyDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            // List all societies
            List<Society> societies = societyDAO.getAllSocieties();
            request.setAttribute("societies", societies);
            request.getRequestDispatcher("/societies.jsp").forward(request, response);
        } else if (action.equals("view")) {
            // View a specific society
            String societyIdStr = request.getParameter("id");
            if (societyIdStr != null && !societyIdStr.trim().isEmpty()) {
                try {
                    int societyId = Integer.parseInt(societyIdStr);
                    Society society = societyDAO.getSocietyById(societyId);

                    if (society != null) {
                        request.setAttribute("society", society);
                        request.getRequestDispatcher("/society-details.jsp").forward(request, response);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/societies");
                    }
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/societies");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/societies");
            }
        }
    }
}