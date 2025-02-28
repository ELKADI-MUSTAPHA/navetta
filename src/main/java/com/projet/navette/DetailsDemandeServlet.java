package com.projet.navette;

import com.projet.dao.DemandeDAO;
import com.projet.dao.OffreDAO;
import com.projet.model.Demande;
import com.projet.model.Offre;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/detailsDemande")
public class DetailsDemandeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int demandeId = Integer.parseInt(request.getParameter("demande_id"));

        // Récupérer les détails de la demande
        DemandeDAO demandeDAO = new DemandeDAO();
        Demande demande = demandeDAO.getDemandeById(demandeId);

        // Récupérer les offres associées à la demande
        OffreDAO offreDAO = new OffreDAO();
        List<Offre> offres = offreDAO.getOffresByDemandeId(demandeId);

        // Transmettre les données à la page JSP
        request.setAttribute("demande", demande);
        request.setAttribute("offres", offres);
        request.getRequestDispatcher("detailsDemande.jsp").forward(request, response);
    }
}