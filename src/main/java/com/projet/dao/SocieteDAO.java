package com.projet.dao;

import com.projet.model.Societe;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SocieteDAO {
    private String url = "jdbc:mysql://localhost:3306/navette?allowPublicKeyRetrieval=true&useSSL=false";
    private String user = "admin1";
    private String password = "Admin@123";

    // Méthode pour récupérer une société par son ID
    public Societe getSocieteById(int societeId) {
        String sql = "SELECT * FROM societe WHERE id = ?";
        Societe societe = null;

        try {
            try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, societeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                societe = new Societe();
                societe.setId(rs.getInt("id"));
                societe.setNom(rs.getString("societe_nom"));
                societe.setEmail(rs.getString("societe_email"));
                societe.setTelephone(rs.getString("telephone"));
                societe.setMotDePasse(rs.getString("mot_de_passe"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return societe;
    }

    public Societe getSocieteByNavetteId(int navetteId) {
        String sql = "SELECT s.id, s.societe_nom, s.societe_email, s.telephone FROM societe s " +
                     "JOIN navette n ON s.id = n.societe_id " +
                     "WHERE n.id = ?";

        try {
            try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, navetteId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Societe societe = new Societe();
                    societe.setId(rs.getInt("id"));
                    societe.setNom(rs.getString("societe_nom"));
                    societe.setEmail(rs.getString("societe_email"));
                    societe.setTelephone(rs.getString("telephone"));
                    return societe;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // Méthode pour récupérer une société par son email
    public Societe getSocieteByEmail(String email) {
        String sql = "SELECT * FROM societe WHERE societe_email = ?";
        Societe societe = null;

        try {
            try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                societe = new Societe();
                societe.setId(rs.getInt("id"));
                societe.setNom(rs.getString("societe_nom"));
                societe.setTelephone(rs.getString("telephone"));
                societe.setEmail(rs.getString("societe_email"));
                societe.setMotDePasse(rs.getString("mot_de_passe"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return societe;
    }
}