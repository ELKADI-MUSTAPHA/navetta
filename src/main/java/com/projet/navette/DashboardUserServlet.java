package com.projet.navette;

import com.projet.dao.DemandeDAO;
import com.projet.dao.NavetteDAO;
import com.projet.model.Demande;
import com.projet.model.Navette;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/dashboardUser")
public class DashboardUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer utilisateurId = (Integer) session.getAttribute("user_id");

        if (utilisateurId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        NavetteDAO navetteDAO = new NavetteDAO();
        List<Navette> navettes = navetteDAO.getNavettesByUserId(utilisateurId);

        DemandeDAO demandeDAO = new DemandeDAO();
        List<Demande> demandes = demandeDAO.getDemandesByUtilisateurId(utilisateurId);

        request.setAttribute("navettes", navettes);
        request.setAttribute("demandes", demandes);

        String message = (String) session.getAttribute("message");
        if (message != null) {
            request.setAttribute("message", message);
            session.removeAttribute("message");
        }

        request.getRequestDispatcher("dashboardUser.jsp").forward(request, response);
    }
}