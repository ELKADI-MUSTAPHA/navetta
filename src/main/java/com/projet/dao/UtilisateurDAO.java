package com.projet.dao;

import com.projet.model.Utilisateur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {
    private String url = "jdbc:mysql://localhost:3306/navette?allowPublicKeyRetrieval=true&useSSL=false";
    private String user = "admin1";
    private String password = "Admin@123";

    /**
     * Récupère la liste des utilisateurs abonnés à une navette spécifique.
     *
     * @param navetteId L'ID de la navette.
     * @return Une liste d'utilisateurs abonnés.
     */
    public List<Utilisateur> getUtilisateursByNavetteId(int navetteId) {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT u.id, u.nom, u.email, u.telephone FROM utilisateur u " +
                     "JOIN abonnement a ON u.id = a.utilisateur_id " +
                     "WHERE a.navette_id = ?";

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
                while (rs.next()) {
                    Utilisateur utilisateur = new Utilisateur();
                    utilisateur.setId(rs.getInt("id"));
                    utilisateur.setNom(rs.getString("nom"));
                    utilisateur.setEmail(rs.getString("email"));
                    utilisateur.setTelephone(rs.getString("telephone"));
                    utilisateurs.add(utilisateur);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }
}