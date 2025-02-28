package com.projet.navette;

import com.projet.dao.AbonnementDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ConfirmerAbonnementServlet")
public class ConfirmerAbonnementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int abonnementId = Integer.parseInt(request.getParameter("id"));

        AbonnementDAO abonnementDAO = new AbonnementDAO();
        boolean success = abonnementDAO.confirmerAbonnement(abonnementId);

        if (success) {
            response.sendRedirect("detailsNavette?id="+request.getParameter("navette_id"));
        } else {
            response.sendRedirect("detailsNavette?id="+request.getParameter("navette_id"));
        }
    }
}
