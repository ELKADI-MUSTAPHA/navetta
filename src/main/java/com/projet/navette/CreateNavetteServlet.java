package com.projet.navette;

import com.projet.dao.NavetteDAO;
import com.projet.model.Navette;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/createNavette")
public class CreateNavetteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;

        try {
            if (session.getAttribute("societe_id") == null) {
                request.setAttribute("status", "societeNotFound");
                dispatcher = request.getRequestDispatcher("navetteCreation.jsp");
                dispatcher.forward(request, response);
                return;
            }

            int societeId = (int) session.getAttribute("societe_id");
            String villeDepart = request.getParameter("ville_depart");
            String villeArrivee = request.getParameter("ville_arrivee");
            String heureDepart = request.getParameter("heure_depart");
            String heureArrivee = request.getParameter("heure_arrivee");
            String periodeDebut = request.getParameter("periode_debut");
            String periodeFin = request.getParameter("periode_fin");
            String description = request.getParameter("description");
            int nombreAbonnes = Integer.parseInt(request.getParameter("nombre_abonnes"));
            
            
            Navette navette = new Navette();
            navette.setSocieteId(societeId);
            navette.setVilleDepart(villeDepart);
            navette.setVilleArrivee(villeArrivee);
            navette.setHeureDepart(heureDepart);
            navette.setHeureArrivee(heureArrivee);
            navette.setPeriodeDebut(periodeDebut);
            navette.setPeriodeFin(periodeFin);
            navette.setDescription(description);
            navette.setNombreAbonnes(nombreAbonnes);
            navette.setPlacesDisponibles(nombreAbonnes);
            navette.setPrix(Double.parseDouble(request.getParameter("prix")));
            
            NavetteDAO navetteDAO = new NavetteDAO();
            boolean isCreated = navetteDAO.createNavette(navette);

            if (isCreated) {
                session.setAttribute("status", "success");
                response.sendRedirect(request.getContextPath() + "/dashboardSociete");
            } else {
                request.setAttribute("status", "failed");
                dispatcher = request.getRequestDispatcher("navetteCreation.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("status", "error");
            dispatcher = request.getRequestDispatcher("navetteCreation.jsp");
            dispatcher.forward(request, response);
        }
    }
}