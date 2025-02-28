package com.projet.dao;

import com.projet.model.Demande;
import com.projet.model.Navette;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DemandeDAO {
    private String url = "jdbc:mysql://localhost:3306/navette?allowPublicKeyRetrieval=true&useSSL=false";
    private String user = "admin1";
    private String password = "Admin@123";
    // Méthode pour récupérer toutes les Demandes (pour la page d'accueil des societes)
    public List<Demande> getAllDemandes() {
        List<Demande> demandes = new ArrayList<>();
        String sql = "SELECT * FROM demande WHERE periode_debut >= CURDATE()"; // Filtre pour les demandes non dépassées

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            	Demande demande = mapResultSetToDemande(rs);
            	demandes.add(demande);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return demandes;
    }
    // Méthode pour vérifier les doublons
    private boolean isDuplicateDemande(Demande demande) {
        String sql = "SELECT COUNT(*) FROM demande WHERE utilisateur_id = ? AND ville_depart = ? AND ville_arrivee = ? " +
                     "AND heure_depart = ? AND heure_arrivee = ? AND periode_debut = ? AND periode_fin = ? AND description = ?";

        try {
            try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, demande.getUtilisateurId());
            stmt.setString(2, demande.getVilleDepart());
            stmt.setString(3, demande.getVilleArrivee());
            stmt.setTime(4, demande.getHeureDepart());
            stmt.setTime(5, demande.getHeureArrivee());
            stmt.setDate(6, demande.getPeriodeDebut());
            stmt.setDate(7, demande.getPeriodeFin());
            stmt.setString(8, demande.getDescription());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // Si count > 0, une demande similaire existe déjà
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createDemande(Demande demande) {
        if (isDuplicateDemande(demande)) {
            return false;
        }

        String sql = "INSERT INTO demande (utilisateur_id, ville_depart, ville_arrivee, heure_depart, heure_arrivee, periode_debut, periode_fin, description, prix) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, demande.getUtilisateurId());
            stmt.setString(2, demande.getVilleDepart());
            stmt.setString(3, demande.getVilleArrivee());
            stmt.setTime(4, demande.getHeureDepart());
            stmt.setTime(5, demande.getHeureArrivee());
            stmt.setDate(6, demande.getPeriodeDebut());
            stmt.setDate(7, demande.getPeriodeFin());
            stmt.setString(8, demande.getDescription());
            stmt.setDouble(9, demande.getPrix()); // Ajout du prix

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Demande> getDemandesByUtilisateurId(int utilisateurId) {
        List<Demande> demandes = new ArrayList<>();
        String sql = "SELECT * FROM demande WHERE utilisateur_id = ?";

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
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Demande demande = new Demande();
                demande.setId(rs.getInt("id"));
                demande.setUtilisateurId(rs.getInt("utilisateur_id"));
                demande.setVilleDepart(rs.getString("ville_depart"));
                demande.setVilleArrivee(rs.getString("ville_arrivee"));
                demande.setHeureDepart(rs.getTime("heure_depart"));
                demande.setHeureArrivee(rs.getTime("heure_arrivee"));
                demande.setPeriodeDebut(rs.getDate("periode_debut"));
                demande.setPeriodeFin(rs.getDate("periode_fin"));
                demande.setDescription(rs.getString("description"));
                demande.setPrix(rs.getInt("prix"));
                demandes.add(demande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return demandes;
    }
    
    public Demande getDemandeById(int demandeId) {
        String sql = "SELECT * FROM demande WHERE id = ?";
        Demande demande = null;

        try {
            try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, demandeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                demande = mapResultSetToDemande(rs); // Utiliser la méthode utilitaire
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return demande;
    }
    
    public boolean deleteDemande(int demandeId) {
        String sql = "DELETE FROM demande WHERE id = ?";
        try {
        	try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, demandeId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean hasAcceptedOffer(int demandeId) {
        String sql = "SELECT COUNT(*) FROM offre WHERE demande_id = ? AND statut = 'accepté'";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, demandeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // Retourne true si une offre acceptée existe
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean isDemandeOwner(int demandeId, int utilisateurId) {
        String sql = "SELECT COUNT(*) FROM demande WHERE id = ? AND utilisateur_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, demandeId);
            stmt.setInt(2, utilisateurId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // Retourne true si l'utilisateur est le créateur
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private Demande mapResultSetToDemande(ResultSet rs) throws SQLException {
        Demande demande = new Demande();
        demande.setId(rs.getInt("id"));
        demande.setUtilisateurId(rs.getInt("utilisateur_id"));
        demande.setVilleDepart(rs.getString("ville_depart"));
        demande.setVilleArrivee(rs.getString("ville_arrivee"));
        demande.setHeureDepart(rs.getTime("heure_depart"));
        demande.setHeureArrivee(rs.getTime("heure_arrivee"));
        demande.setPeriodeDebut(rs.getDate("periode_debut"));
        demande.setPeriodeFin(rs.getDate("periode_fin"));
        demande.setDescription(rs.getString("description"));
        demande.setPrix(rs.getInt("prix"));
        return demande;
    }
}