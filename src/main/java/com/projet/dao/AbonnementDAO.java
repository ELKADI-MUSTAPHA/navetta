package com.projet.dao;

import com.projet.model.Abonnement;
import com.projet.model.Demande;
import com.projet.model.Utilisateur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbonnementDAO {
    private String url = "jdbc:mysql://localhost:3306/navette?allowPublicKeyRetrieval=true&useSSL=false";
    private String user = "admin1";
    private String password = "Admin@123";

    public List<Abonnement> getAllAbonnements() {
        List<Abonnement> abonnements = new ArrayList<>();
        String sql = "SELECT * FROM abonnement";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            	Abonnement abonnement = mapResultSetToAbonnement(rs);
                abonnements.add(abonnement);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return abonnements;
    }
    public List<Abonnement> getAbonnementsByNavetteId(int navetteId) {
        List<Abonnement> abonnements = new ArrayList<>();
        String sql = "SELECT a.*, u.id AS utilisateur_id, u.nom, u.email, u.telephone " +
                     "FROM abonnement a " +
                     "JOIN utilisateur u ON a.utilisateur_id = u.id " +
                     "WHERE a.navette_id = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, navetteId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Abonnement abonnement = new Abonnement();
                abonnement.setId(rs.getInt("id"));
                abonnement.setUtilisateurId(rs.getInt("utilisateur_id"));
                abonnement.setNavetteId(rs.getInt("navette_id"));
                abonnement.setStatus(rs.getString("status"));

                // ✅ Fetch and set Utilisateur
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setId(rs.getInt("utilisateur_id"));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setEmail(rs.getString("email"));
                utilisateur.setTelephone(rs.getString("telephone"));

                abonnement.setUtilisateur(utilisateur); // ✅ Assign user to abonnement

                abonnements.add(abonnement);
            }

            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return abonnements;
    }

    
    // Méthode pour créer un abonnement
    public boolean createAbonnement(int utilisateurId, int navetteId) {
        String sql = "INSERT INTO abonnement (utilisateur_id, navette_id) VALUES (?, ?)";

        try {
        	try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, utilisateurId);
            stmt.setInt(2, navetteId);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour vérifier si un utilisateur est déjà abonné à une navette
    public boolean isAlreadySubscribed(int utilisateurId, int navetteId) {
        String sql = "SELECT COUNT(*) FROM abonnement WHERE utilisateur_id = ? AND navette_id = ?";

        try {
        	try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, utilisateurId);
            stmt.setInt(2, navetteId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteAbonnement(int utilisateurId, int navetteId) {
        String sql = "DELETE FROM abonnement WHERE utilisateur_id = ? AND navette_id = ?";

        try {
        	try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, utilisateurId);
            stmt.setInt(2, navetteId);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0; // Retourne true si l'abonnement a été supprimé
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean confirmerAbonnement(int abonnementId) {
        try {
        	try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
        	String sql = "UPDATE abonnement SET status = 'CONFIRME' WHERE id = ?";
        	Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, abonnementId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String getAbonnementStatus(int utilisateurId, int navetteId) {
        String status = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sql = "SELECT status FROM abonnement WHERE utilisateur_id = ? AND navette_id = ?";
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, utilisateurId);
            stmt.setInt(2, navetteId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                status = rs.getString("status"); // Get the subscription status
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public boolean userHasConfirmedNavette(int utilisateurId, int societeId) {
        String sql = "SELECT COUNT(*) FROM abonnement a " +
                     "JOIN navette n ON a.navette_id = n.id " +
                     "WHERE a.utilisateur_id = ? AND n.societe_id = ? AND a.status = 'CONFIRME'";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, utilisateurId);
            stmt.setInt(2, societeId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private Abonnement mapResultSetToAbonnement(ResultSet rs) throws SQLException {
    	Abonnement abonnement = new Abonnement();
    	abonnement.setId(rs.getInt("id"));
    	abonnement.setUtilisateurId(rs.getInt("utilisateur_id"));
    	abonnement.setNavetteId(rs.getInt("navette_id"));
    	abonnement.setStatus(rs.getString("status"));
        return abonnement;
    }
}