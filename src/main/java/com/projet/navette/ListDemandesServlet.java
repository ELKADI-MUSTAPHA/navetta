package com.projet.navette;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.projet.dao.DemandeDAO;
import com.projet.model.Demande;

/**
 * Servlet implementation class ListNavettesServlet
 */
@WebServlet("/demandes")
public class ListDemandesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 DemandeDAO DemandeDAO = new DemandeDAO();
	        List<Demande> demandes = DemandeDAO.getAllDemandes();
	        request.setAttribute("demandes", demandes);
	        request.getRequestDispatcher("demandes.jsp").forward(request, response);
	}


}
