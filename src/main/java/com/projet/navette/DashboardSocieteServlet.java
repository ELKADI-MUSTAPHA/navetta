package com.projet.navette;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.projet.dao.NavetteDAO;
import com.projet.model.Navette;

@WebServlet("/dashboardSociete")
public class DashboardSocieteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;
        
        if (session.getAttribute("societe_id") == null) {
            request.setAttribute("status", "societeNotFound");
            dispatcher = request.getRequestDispatcher("loginCompany.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        int societeId = (int) session.getAttribute("societe_id");

        NavetteDAO navetteDAO = new NavetteDAO();
        List<Navette> navettes = navetteDAO.getNavettesBySocieteId(societeId);

        request.setAttribute("navettes", navettes);
        dispatcher = request.getRequestDispatcher("dashboardSociete.jsp");
        dispatcher.forward(request, response);
    }
}