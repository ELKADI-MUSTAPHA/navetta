package com.projet.navette;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.projet.dao.CommentaireDAO;

/**
 * Servlet implementation class RateUserServlet
 */
@WebServlet("/RateUserServlet")
public class RateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CommentaireDAO commentaireDAO = new CommentaireDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int societeId = (int) request.getSession().getAttribute("societe_id"); // Get logged-in company ID
        int utilisateurId = Integer.parseInt(request.getParameter("utilisateurId"));
        int rating = Integer.parseInt(request.getParameter("rating"));

        boolean alreadyRated = commentaireDAO.hasCompanyRatedUser(societeId, utilisateurId);

        if (!alreadyRated) {
        	commentaireDAO.addUserRating(societeId, utilisateurId, rating);
        }
        
        response.sendRedirect("detailsNavette?id="+request.getParameter("navette_id")); // Redirect back to the page
    }
}
