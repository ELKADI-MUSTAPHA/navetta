package com.projet.navette;

import com.projet.dao.DemandeDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/supprimerDemande")
public class SupprimerDemandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int demandeId = Integer.parseInt(request.getParameter("demande_id"));

        DemandeDAO demandeDAO = new DemandeDAO();
        if (demandeDAO.deleteDemande(demandeId)) {
            request.getSession().setAttribute("message", "Demande supprimée avec succès !");
        } else {
            request.getSession().setAttribute("message", "Erreur lors de la suppression de la demande.");
        }

        response.sendRedirect(request.getContextPath() + "/dashboardUser");
    }
}