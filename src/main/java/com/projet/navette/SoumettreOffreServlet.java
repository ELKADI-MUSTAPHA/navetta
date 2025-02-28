package com.projet.navette;

import com.projet.dao.OffreDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/soumettreOffre")
public class SoumettreOffreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private OffreDAO offreDAO = new OffreDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
        int demandeId = Integer.parseInt(request.getParameter("demande_id"));
        Integer conectedSocieteId = (Integer) session.getAttribute("societe_id");
        if (conectedSocieteId == null) {
            // Rediriger vers la page de connexion si l'utilisateur n'est pas connecté
            response.sendRedirect("loginCompany.jsp");
            return;
        }
        // Logique pour soumettre une offre
        boolean success = offreDAO.soumettreOffre(conectedSocieteId, demandeId);

        if (success) {
            response.sendRedirect("demandes");
        } else {
            response.sendRedirect("demandes");
        }
    }
}