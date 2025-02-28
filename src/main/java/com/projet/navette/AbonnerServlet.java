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

@WebServlet("/abonner")
public class AbonnerServlet extends HttpServlet {
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

        // Vérifier si l'utilisateur est déjà abonné
        if (abonnementDAO.isAlreadySubscribed(utilisateurId, navetteId)) {
            request.setAttribute("message", "Vous êtes déjà abonné à cette navette.");
        } else {
            // Créer l'abonnement
            if (abonnementDAO.createAbonnement(utilisateurId, navetteId)) {
                // Mettre à jour le nombre de places disponibles
                navetteDAO.decrementPlacesDisponibles(navetteId);
                request.setAttribute("message", "Abonnement réussi !");
            } else {
                request.setAttribute("message", "Erreur lors de l'abonnement.");
            }
        }

        // Rediriger vers la page d'accueil
        request.getRequestDispatcher("home").forward(request, response);
    }
}