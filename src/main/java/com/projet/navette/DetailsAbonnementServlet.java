package com.projet.navette;

import com.projet.dao.NavetteDAO;
import com.projet.dao.SocieteDAO;
import com.projet.model.Navette;
import com.projet.model.Societe;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/detailsAbonnement")
public class DetailsAbonnementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int navetteId = Integer.parseInt(request.getParameter("navette_id"));

        // Récupérer les détails de la navette
        NavetteDAO navetteDAO = new NavetteDAO();
        Navette navette = navetteDAO.getNavetteById(navetteId);

        // Récupérer les détails de la société
        SocieteDAO societeDAO = new SocieteDAO();
        Societe societe = societeDAO.getSocieteById(navette.getSocieteId());

        // Transmettre les détails à la page JSP
        request.setAttribute("navette", navette);
        request.setAttribute("societe", societe);
        request.getRequestDispatcher("detailsAbonnement.jsp").forward(request, response);
    }
}