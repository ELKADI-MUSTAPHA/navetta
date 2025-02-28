package com.projet.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("mot_de_passe");
        String telephone = request.getParameter("telephone");
        HttpSession session = request.getSession();

        // MySQL database connection details
        String url = "jdbc:mysql://localhost:3306/navette?allowPublicKeyRetrieval=true&useSSL=false";
        String user = "admin1";
        String password = "Admin@123"; 
        RequestDispatcher dispatcher = null;
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);

            // Step 1: Check if email already exists
            String checkEmailSQL = "SELECT email FROM Utilisateur WHERE email = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkEmailSQL);
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                request.setAttribute("status", "emailExists");
                dispatcher = request.getRequestDispatcher("registration.jsp");
            } else {
                // Step 2: Hash password using BCrypt
                String hashedPassword = BCrypt.hashpw(motDePasse, BCrypt.gensalt(12));

                // Step 3: Insert user into database
                String sql = "INSERT INTO Utilisateur (nom, email, telephone, mot_de_passe) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setString(1, nom);
                stmt.setString(2, email);
                stmt.setString(3, telephone);
                stmt.setString(4, hashedPassword); // Store the hashed password

                int rowCount = stmt.executeUpdate();
                if (rowCount > 0) {
                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int userId = generatedKeys.getInt(1);
                        session.setAttribute("user_id", userId);
                    }
                    session.setAttribute("nom", nom);
                    dispatcher = request.getRequestDispatcher("index.jsp");
                } else {
                    dispatcher = request.getRequestDispatcher("registration.jsp");
                }
            }

            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
