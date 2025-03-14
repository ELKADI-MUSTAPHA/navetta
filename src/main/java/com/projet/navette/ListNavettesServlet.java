package com.projet.navette;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.projet.dao.NavetteDAO;
import com.projet.model.Navette;

/**
 * Servlet implementation class ListNavettesServlet
 */
@WebServlet("/home")
public class ListNavettesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 NavetteDAO navetteDAO = new NavetteDAO();
	        List<Navette> navettes = navetteDAO.getAllNavettes();
	        request.setAttribute("navettes", navettes);
	        request.getRequestDispatcher("home.jsp").forward(request, response);
	}


}
