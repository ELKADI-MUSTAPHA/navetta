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

import org.mindrot.jbcrypt.BCrypt; // Import BCrypt for password hashing

@WebServlet("/registerCompany")
public class RegistrationCompanyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String companyName = request.getParameter("societe_nom");
        String companyEmail = request.getParameter("societe_email");
        String phone = request.getParameter("telephone");
        String password = request.getParameter("mot_de_passe");
        
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;

        // Database credentials
        String url = "jdbc:mysql://localhost:3306/navette?allowPublicKeyRetrieval=true&useSSL=false";
        String dbUser = "admin1";
        String dbPassword = "Admin@123"; 

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword)) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Check if email already exists
            String checkEmailSQL = "SELECT societe_email FROM societe WHERE societe_email = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkEmailSQL)) {
                checkStmt.setString(1, companyEmail);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    request.setAttribute("status", "emailExists");
                    dispatcher = request.getRequestDispatcher("registrationCompany.jsp");
                } else {
                    // Hash the password before storing it
                    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

                    // Insert new company
                    String insertSQL = "INSERT INTO societe (societe_nom, societe_email, telephone, mot_de_passe) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement stmt = conn.prepareStatement(insertSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                        stmt.setString(1, companyName);
                        stmt.setString(2, companyEmail);
                        stmt.setString(3, phone);
                        stmt.setString(4, hashedPassword);

                        int rowCount = stmt.executeUpdate();
                        if (rowCount > 0) {
                            ResultSet generatedKeys = stmt.getGeneratedKeys();
                            if (generatedKeys.next()) {
                                int companyId = generatedKeys.getInt(1); 
                                session.setAttribute("societe_id", companyId);
                            }
                            session.setAttribute("nom", companyName);
                            request.setAttribute("status", "success");
                            dispatcher = request.getRequestDispatcher("index.jsp");
                        } else {
                            request.setAttribute("status", "failed");
                            dispatcher = request.getRequestDispatcher("registrationCompany.jsp");
                        }
                    }
                }
            }
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirect to error page if something goes wrong
        }
    }
}
