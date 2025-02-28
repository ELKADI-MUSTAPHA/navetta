package com.projet.dao;

import com.projet.model.Commentaire;
import com.projet.model.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentaireDAO {
    private String url = "jdbc:mysql://localhost:3306/navette?allowPublicKeyRetrieval=true&useSSL=false";
    private String user = "admin1";
    private String password = "Admin@123";

    public List<Commentaire> getCommentairesBySociete(int societeId) { 
        List<Commentaire> commentaires = new ArrayList<>();
        String sql = "SELECT c.*, u.id AS utilisateur_id, u.nom, u.email " +
                     "FROM commentaire c " +
                     "JOIN utilisateur u ON c.utilisateur_id = u.id " +
                     "WHERE c.societe_id = ? " +
                     "ORDER BY c.date_creation DESC";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, societeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Commentaire commentaire = mapResultSetToCommentaire(rs);

                // Create Utilisateur object and set user details
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setId(rs.getInt("utilisateur_id"));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setEmail(rs.getString("email"));

                // Set Utilisateur in Commentaire
                commentaire.setUtilisateur(utilisateur);

                commentaires.add(commentaire);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return commentaires;
    }


    public boolean createCommentaire(Commentaire newComment) {
        String sql = "INSERT INTO commentaire (utilisateur_id, societe_id, comment, rating, date_creation) VALUES (?, ?, ?, ?, NOW())";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, newComment.getUtilisateurId());
            stmt.setInt(2, newComment.getSocieteId());
            stmt.setString(3, newComment.getComment());
            stmt.setInt(4, newComment.getRating());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCommentaire(int commentaireId, int utilisateurId) {
        String sql = "DELETE FROM commentaire WHERE id = ? AND utilisateur_id = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, commentaireId);
            stmt.setInt(2, utilisateurId);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean hasCompanyRatedUser(int societeId, int utilisateurId) {
        String sql = "SELECT COUNT(*) FROM user_rating WHERE societe_id = ? AND utilisateur_id = ?";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, societeId);
            stmt.setInt(2, utilisateurId);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // If count > 0, the company has already rated the user
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addUserRating(int societeId, int utilisateurId, int rating) {
        String sql = "INSERT INTO user_rating (societe_id, utilisateur_id, rating) VALUES (?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, societeId);
            stmt.setInt(2, utilisateurId);
            stmt.setInt(3, rating);
            
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public double getAverageRating(int utilisateurId) {
        String sql = "SELECT AVG(rating) FROM user_rating WHERE utilisateur_id = ?";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, utilisateurId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0; // Default if no ratings exist
    }

    
    private Commentaire mapResultSetToCommentaire(ResultSet rs) throws SQLException {
        Commentaire commentaire = new Commentaire();
        commentaire.setId(rs.getInt("id"));
        commentaire.setUtilisateurId(rs.getInt("utilisateur_id"));
        commentaire.setSocieteId(rs.getInt("societe_id"));
        commentaire.setComment(rs.getString("comment"));
        commentaire.setDateCreation(rs.getString("date_creation"));
        commentaire.setRating(rs.getInt("rating"));
        return commentaire;
    }
}
