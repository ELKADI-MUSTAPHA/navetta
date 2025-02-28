package com.projet.navette;

import com.projet.dao.OffreDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mettreAJourStatutOffre")
public class MettreAJourStatutOffreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private OffreDAO offreDAO = new OffreDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int offreId = Integer.parseInt(request.getParameter("offre_id"));
        String statut = request.getParameter("statut"); // 'accepté' ou 'non_accepté'
        int demandeId = Integer.parseInt(request.getParameter("demande_id"));

        // Mettre à jour le statut de l'offre
        boolean success = offreDAO.mettreAJourStatutOffre(offreId, statut);

        if (success) {
            // Si l'offre est acceptée, marquer toutes les autres offres comme 'non_accepté'
            if ("accepté".equals(statut)) {
                offreDAO.marquerAutresOffresCommeNonAcceptees(demandeId, offreId);
            }
            response.sendRedirect("detailsDemande?demande_id="+demandeId);
        } else {
            response.sendRedirect("detailsDemande?demande_id="+demandeId);
        }
    }
}