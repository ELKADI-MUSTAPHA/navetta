package com.projet.navette;

import com.projet.dao.DemandeDAO;
import com.projet.model.Demande;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

@WebServlet("/createDemande")
public class CreateDemandeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer utilisateurId = (Integer) session.getAttribute("user_id"); // ID de l'utilisateur connecté

        if (utilisateurId == null) {
            // Rediriger vers la page de connexion si l'utilisateur n'est pas connecté
            response.sendRedirect("login.jsp");
            return;
        }

        // Récupérer les données du formulaire
        String villeDepart = request.getParameter("ville_depart");
        String villeArrivee = request.getParameter("ville_arrivee");
        Time heureDepart = Time.valueOf(request.getParameter("heure_depart") + ":00");
        Time heureArrivee = Time.valueOf(request.getParameter("heure_arrivee") + ":00");
        Date periodeDebut = Date.valueOf(request.getParameter("periode_debut"));
        Date periodeFin = Date.valueOf(request.getParameter("periode_fin"));
        String description = request.getParameter("description");

        // Créer une demande
        Demande demande = new Demande();
        demande.setUtilisateurId(utilisateurId);
        demande.setVilleDepart(villeDepart);
        demande.setVilleArrivee(villeArrivee);
        demande.setHeureDepart(heureDepart);
        demande.setHeureArrivee(heureArrivee);
        demande.setPeriodeDebut(periodeDebut);
        demande.setPeriodeFin(periodeFin);
        demande.setDescription(description);
        demande.setPrix(Double.parseDouble(request.getParameter("prix")));
        
        // Enregistrer la demande dans la base de données
        DemandeDAO demandeDAO = new DemandeDAO();
        if (demandeDAO.createDemande(demande)) {
            session.setAttribute("message", "Demande créée avec succès !");
            response.sendRedirect(request.getContextPath() + "/dashboardUser");
        } else {
            session.setAttribute("message", "Erreur lors de la création de la demande.");
            response.sendRedirect(request.getContextPath() + "/demandeCreation.jsp");
        }
    }
}