package com.projet.navette;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.projet.dao.CommentaireDAO;
import com.projet.model.Commentaire;

@WebServlet("/AjouterCommentaireServlet")
public class AjouterCommentaireServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");

        if (userId == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if not authenticated
            return;
        }

        int societeId = Integer.parseInt(request.getParameter("societe_id"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");
        // userId, societeId, comment, rating
        Commentaire newComment = new Commentaire();
        newComment.setUtilisateurId(userId);
        newComment.setSocieteId(societeId);
        newComment.setComment(comment);
        newComment.setRating(rating);

        CommentaireDAO commentaireDAO = new CommentaireDAO();
        if (commentaireDAO.createCommentaire(newComment)) {
            session.setAttribute("message", "Commentaire créée avec succès !");
            response.sendRedirect("detailsNavette?id="+request.getParameter("navette_id"));
        } else {
            session.setAttribute("message", "Erreur lors de la création de la commentaire.");
            response.sendRedirect("detailsNavette?id="+request.getParameter("navette_id"));
        }
    }
}
