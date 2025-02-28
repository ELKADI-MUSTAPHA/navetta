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

import org.mindrot.jbcrypt.BCrypt; // Import BCrypt for password checking

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String enteredPassword = request.getParameter("mot_de_passe"); // User's entered password
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;

        // Database credentials
        String url = "jdbc:mysql://localhost:3306/navette?allowPublicKeyRetrieval=true&useSSL=false";
        String dbUser = "admin1";
        String dbPassword = "Admin@123";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword)) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 1: Retrieve the hashed password from the database
            String sql = "SELECT id, nom, mot_de_passe FROM Utilisateur WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, email);
                ResultSet result = stmt.executeQuery();

                if (result.next()) {
                    String storedHashedPassword = result.getString("mot_de_passe"); // Stored hashed password
                    
                    // Step 2: Compare the entered password with the stored hashed password
                    if (BCrypt.checkpw(enteredPassword, storedHashedPassword)) {
                        session.setAttribute("nom", result.getString("nom"));
                        session.setAttribute("user_id", result.getInt("id"));
                        dispatcher = request.getRequestDispatcher("index.jsp");
                    } else {
                        request.setAttribute("status", "failed"); // Wrong password
                        dispatcher = request.getRequestDispatcher("login.jsp");
                    }
                } else {
                    request.setAttribute("status", "failed"); // No user found
                    dispatcher = request.getRequestDispatcher("login.jsp");
                }
            }

            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirect to error page if an issue occurs
        }
    }
}
