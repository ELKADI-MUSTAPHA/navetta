package com.projet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projet.model.Offre;
import com.projet.model.Societe;

public class OffreDAO {
    private String url = "jdbc:mysql://localhost:3306/navette?allowPublicKeyRetrieval=true&useSSL=false";
    private String user = "admin1";
    private String password = "Admin@123";

    /**
     * Vérifie si une société a déjà soumis une offre pour une demande.
     *
     * @param societeId L'ID de la société.
     * @param demandeId L'ID de la demande.
     * @return true si une offre a été soumise, sinon false.
     */
    public boolean hasSubmittedOffer(int societeId, int demandeId) {
        String sql = "SELECT COUNT(*) FROM Offre WHERE societe_id = ? AND demande_id = ?";

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
            stmt.setInt(2, demandeId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // Retourne true si une offre existe
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Soumet une offre pour une demande spécifique.
     *
     * @param societeId L'ID de la société.
     * @param demandeId L'ID de la demande.
     * @return true si l'offre a été soumise avec succès, sinon false.
     */
    public boolean soumettreOffre(int societeId, int demandeId) {
        String sql = "INSERT INTO Offre (societe_id, demande_id) VALUES (?, ?)";

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
            stmt.setInt(2, demandeId);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Retourne true si l'insertion a réussi
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean mettreAJourStatutOffre(int offreId, String statut) {
        String sql = "UPDATE Offre SET statut = ? WHERE id = ?";

        try {
            try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, statut);
            stmt.setInt(2, offreId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0; // Retourne true si la mise à jour a réussi
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Marque toutes les offres d'une demande comme 'non_accepté', sauf celle spécifiée.
     *
     * @param demandeId L'ID de la demande.
     * @param offreIdAcceptee L'ID de l'offre acceptée.
     */
    public void marquerAutresOffresCommeNonAcceptees(int demandeId, int offreIdAcceptee) {
        String sql = "UPDATE Offre SET statut = 'non_accepté' WHERE demande_id = ? AND id != ?";

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
            stmt.setInt(2, offreIdAcceptee);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Offre> getOffresByDemandeId(int demandeId) {
        List<Offre> offres = new ArrayList<>();
        String sql = "SELECT * FROM Offre WHERE demande_id = ?";

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
            try (ResultSet rs = stmt.executeQuery()) {
                SocieteDAO societeDAO = new SocieteDAO(); // Créer une instance de SocieteDAO
                while (rs.next()) {
                    Offre offre = new Offre();
                    offre.setId(rs.getInt("id"));
                    offre.setSocieteId(rs.getInt("societe_id"));
                    offre.setDemandeId(rs.getInt("demande_id"));
                    offre.setStatut(rs.getString("statut"));

                    // Récupérer et associer la société à l'offre
                    Societe societe = societeDAO.getSocieteById(offre.getSocieteId());
                    offre.setSociete(societe);

                    offres.add(offre);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offres;
    }
    
    /**
     * Vérifie si une offre acceptée pour une demande appartient à la société connectée.
     *
     * @param demandeId L'ID de la demande.
     * @param societeId L'ID de la société connectée.
     * @return true si l'offre acceptée appartient à la société, sinon false.
     */
    public boolean isAcceptedOfferBySociete(int demandeId, int societeId) {
        String sql = "SELECT COUNT(*) FROM Offre WHERE demande_id = ? AND societe_id = ? AND statut = 'accepté'";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, demandeId);
            stmt.setInt(2, societeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // Retourne true si l'offre acceptée appartient à la société
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
}