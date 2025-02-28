package com.projet.navette;

import com.projet.dao.AbonnementDAO;
import com.projet.dao.NavetteDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/desabonner")
public class DesabonnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer utilisateurId = (Integer) session.getAttribute("user_id"); // ID de l'utilisateur connecté
        int navetteId = Integer.parseInt(request.getParameter("navette_id")); // ID de la navette

        if (utilisateurId == null) {
            // Rediriger vers la page de connexion si l'utilisateur n'est pas connecté
            response.sendRedirect("login.jsp");
            return;
        }

        AbonnementDAO abonnementDAO = new AbonnementDAO();
        NavetteDAO navetteDAO = new NavetteDAO();

        // Supprimer l'abonnement
        if (abonnementDAO.deleteAbonnement(utilisateurId, navetteId)) {
            // Réincrémenter le nombre de places disponibles
            navetteDAO.incrementPlacesDisponibles(navetteId);
            request.setAttribute("message", "Désabonnement réussi !");
        } else {
            request.setAttribute("message", "Erreur lors du désabonnement.");
        }

        // Rediriger vers la page d'accueil
        request.getRequestDispatcher("home").forward(request, response);
    }
}