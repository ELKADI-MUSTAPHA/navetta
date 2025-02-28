package com.projet.navette;

import com.projet.dao.AbonnementDAO;
import com.projet.dao.CommentaireDAO;
import com.projet.dao.NavetteDAO;
import com.projet.dao.SocieteDAO;
import com.projet.dao.UtilisateurDAO;
import com.projet.model.Abonnement;
import com.projet.model.Commentaire;
import com.projet.model.Navette;
import com.projet.model.Societe;
import com.projet.model.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/detailsNavette")
public class DetailsNavetteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NavetteDAO navetteDAO = new NavetteDAO();
    private SocieteDAO societeDAO = new SocieteDAO();
    private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int navetteId = Integer.parseInt(request.getParameter("id"));

        // Récupérer les détails de la navette
        Navette navette = navetteDAO.getNavetteById(navetteId);
        Societe societe = societeDAO.getSocieteByNavetteId(navetteId);
        AbonnementDAO abonnementDAO = new AbonnementDAO();
        CommentaireDAO commentaireDAO = new CommentaireDAO();
        
        // Vérifier le type d'utilisateur connecté
        Integer utilisateurId = (Integer) request.getSession().getAttribute("user_id");
        Integer societeId = (Integer) request.getSession().getAttribute("societe_id");

        // Récupérer les utilisateurs abonnés uniquement si une société est connectée
        // ET si la société est propriétaire de la navette
        List<Utilisateur> utilisateursAbonnes = null;
        List<Abonnement> abonnements = null;
        if (societeId != null && societe != null && societeId == societe.getId()) {
            utilisateursAbonnes = utilisateurDAO.getUtilisateursByNavetteId(navetteId);
            abonnements = abonnementDAO.getAbonnementsByNavetteId(navetteId);
        }

        // Fetch comments for the Société
 
        List<Commentaire> commentaires = commentaireDAO.getCommentairesBySociete(societe.getId());
        
        
        // Check if user has a confirmed subscription
        boolean canComment = false;
        if (utilisateurId != null) {
            canComment = abonnementDAO.userHasConfirmedNavette(utilisateurId, societe.getId());
        }
             
        // Transmettre les données à la JSP
        request.setAttribute("commentaires", commentaires);
        request.setAttribute("canComment", canComment);
        request.setAttribute("navette", navette);
        request.setAttribute("societe", societe);
        request.setAttribute("utilisateursAbonnes", utilisateursAbonnes);
        request.setAttribute("abonnements", abonnements);
        request.setAttribute("isSociete", societeId != null); // Indiquer si l'utilisateur est une société
        request.setAttribute("isProprietaire", societeId != null && societe != null && societeId == societe.getId()); // Indiquer si la société est propriétaire

        request.getRequestDispatcher("/detailsNavette.jsp").forward(request, response);
    }
}