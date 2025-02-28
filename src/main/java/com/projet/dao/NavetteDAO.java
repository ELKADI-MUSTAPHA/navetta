package com.projet.dao;

import com.projet.model.Navette;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NavetteDAO {
    private String url = "jdbc:mysql://localhost:3306/navette?allowPublicKeyRetrieval=true&useSSL=false";
    private String user = "admin1";
    private String password = "Admin@123";

    // Méthode pour récupérer toutes les navettes (pour la page d'accueil des utilisateurs)
    public List<Navette> getAllNavettes() {
        List<Navette> navettes = new ArrayList<>();
        String sql = "SELECT * FROM navette WHERE periode_debut >= CURDATE()"; // Filtre pour les navettes non dépassées

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Navette navette = mapResultSetToNavette(rs);
                navettes.add(navette);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return navettes;
    }

    // Méthode pour récupérer les navettes d'une société spécifique (pour le dashboard de la société)
    public List<Navette> getNavettesBySocieteId(int societeId) {
        List<Navette> navettes = new ArrayList<>();
        String sql = "SELECT * FROM navette WHERE societe_id = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, societeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Navette navette = mapResultSetToNavette(rs);
                navettes.add(navette);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return navettes;
    }
    
    public List<Navette> getNavettesByUserId(int utilisateurId) {
        List<Navette> navettes = new ArrayList<>();
        String sql = "SELECT n.* FROM navette n " +
                     "JOIN abonnement a ON n.id = a.navette_id " +
                     "WHERE a.utilisateur_id = ?";

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
                Navette navette = new Navette();
                navette.setId(rs.getInt("id"));
                navette.setSocieteId(rs.getInt("societe_id"));
                navette.setVilleDepart(rs.getString("ville_depart"));
                navette.setVilleArrivee(rs.getString("ville_arrivee"));
                navette.setHeureDepart(rs.getString("heure_depart"));
                navette.setHeureArrivee(rs.getString("heure_arrivee"));
                navette.setPeriodeDebut(rs.getString("periode_debut"));
                navette.setPeriodeFin(rs.getString("periode_fin"));
                navette.setDescription(rs.getString("description"));
                navette.setPrix(rs.getInt("prix"));
                navette.setNombreAbonnes(rs.getInt("nombre_abonnes"));
                navette.setPlacesDisponibles(rs.getInt("places_disponibles"));
                navettes.add(navette);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return navettes;
    }

    public boolean createNavette(Navette navette) {
        String sql = "INSERT INTO navette (societe_id, ville_depart, ville_arrivee, heure_depart, heure_arrivee, periode_debut, periode_fin, description, nombre_abonnes, places_disponibles, prix) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, navette.getSocieteId());
            stmt.setString(2, navette.getVilleDepart());
            stmt.setString(3, navette.getVilleArrivee());
            stmt.setString(4, navette.getHeureDepart());
            stmt.setString(5, navette.getHeureArrivee());
            stmt.setString(6, navette.getPeriodeDebut());
            stmt.setString(7, navette.getPeriodeFin());
            stmt.setString(8, navette.getDescription());
            stmt.setInt(9, navette.getNombreAbonnes());
            stmt.setInt(10, navette.getPlacesDisponibles());
            stmt.setDouble(11, navette.getPrix()); // Ajout du prix

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour récupérer une navette par son ID
    public Navette getNavetteById(int navetteId) {
        String sql = "SELECT * FROM navette WHERE id = ?";
        Navette navette = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, navetteId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                navette = mapResultSetToNavette(rs);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return navette;
    }

    // Méthode pour décrémenter le nombre de places disponibles
    public boolean decrementPlacesDisponibles(int navetteId) {
        String sql = "UPDATE navette SET places_disponibles = places_disponibles - 1 WHERE id = ? AND places_disponibles > 0";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, navetteId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean incrementPlacesDisponibles(int navetteId) {
        String sql = "UPDATE Navette SET places_disponibles = places_disponibles + 1 WHERE id = ?";

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
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    private Navette mapResultSetToNavette(ResultSet rs) throws SQLException {
        Navette navette = new Navette();
        navette.setId(rs.getInt("id"));
        navette.setSocieteId(rs.getInt("societe_id"));
        navette.setVilleDepart(rs.getString("ville_depart"));
        navette.setVilleArrivee(rs.getString("ville_arrivee"));
        navette.setHeureDepart(rs.getString("heure_depart"));
        navette.setHeureArrivee(rs.getString("heure_arrivee"));
        navette.setPeriodeDebut(rs.getString("periode_debut"));
        navette.setPeriodeFin(rs.getString("periode_fin"));
        navette.setDescription(rs.getString("description"));
        navette.setPrix(rs.getInt("prix"));
        navette.setNombreAbonnes(rs.getInt("nombre_abonnes"));
        navette.setPlacesDisponibles(rs.getInt("places_disponibles"));
        return navette;
    }
}