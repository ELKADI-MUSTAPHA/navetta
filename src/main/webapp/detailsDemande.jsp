<!-- add session validation here -->
<%
	if(session.getAttribute("user_id")==null){
		response.sendRedirect("login.jsp");
	}
%>
<%@page import="com.projet.dao.DemandeDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.projet.model.Demande" %>
<%@ page import="com.projet.model.Offre" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat, java.sql.Time" %>

<html>
<head>
    <title>Détails de la Demande</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 2rem;
            margin-top: 2rem;
        }
        h2 {
            color: #343a40;
            margin-bottom: 1.5rem;
        }
        .detail-item {
            margin-bottom: 1rem;
        }
        .detail-item strong {
            color: #495057;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #004085;
        }
        .btn-success {
            background-color: #28a745;
            border-color: #28a745;
        }
        .btn-danger {
            background-color: #dc3545;
            border-color: #dc3545;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="text-center">Détails de la Demande</h2>
        <%
            Demande demande = (Demande) request.getAttribute("demande");

		    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm"); // Hours and minutes only
		    Time heureDepart = demande.getHeureDepart(); // Convert String to Time
		    Time heureArrivee = demande.getHeureArrivee(); // Convert String to Time
            if (demande != null) {
        %>
		<div class="table-responsive">
		    <table class="table table-bordered table-striped mt-4">
		        <tbody>
		            <tr>
		                <th>Trajet</th>
		                <td><%= demande.getVilleDepart() %> à <%= demande.getVilleArrivee() %></td>
		            </tr>
		            <tr>
		                <th>Heure de trajet</th>
		                <td><%= timeFormat.format(demande.getHeureDepart()) %> à <%= timeFormat.format(demande.getHeureArrivee()) %></td>
		            </tr>
		            <tr>
		                <th>Période</th>
		                <td><%= demande.getPeriodeDebut() %> / <%= demande.getPeriodeFin() %></td>
		            </tr>
		            <tr>
		                <th>Description</th>
		                <td><%= demande.getDescription() %></td>
		            </tr>
		            <tr>
		                <th>Prix</th>
		                <td><strong><%= demande.getPrix() %>DH</strong></td>
		            </tr>
		        </tbody>
		    </table>
		</div>

        <%
            } else {
        %>
                <p class="text-danger text-center">Aucune donnée trouvée.</p>
        <%
            }
        %>

        <!-- Tableau des offres -->
        <h3 class="mt-4">Offres Soumises</h3>
        <table class="table">
            <thead>
                <tr>
                    <th>Société</th>
                    <th>Telephone</th>
                    <th>Statut</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
			 	<%
				 	 // Instancier DemandeDAO
				    DemandeDAO demandeDAO = new DemandeDAO();
			 		Integer utilisateurId = (Integer) session.getAttribute("user_id");
				    List<Offre> offres = (List<Offre>) request.getAttribute("offres");
				    boolean isDemandeOwner = false;
				    if (offres != null && !offres.isEmpty()) {
				        for (Offre offre : offres) {
				        	if(utilisateurId != null){
				        		isDemandeOwner = demandeDAO.isDemandeOwner(demande.getId(), utilisateurId);
				        	}
				            
				%>
				            <tr>
				                <td><%= offre.getSociete().getNom() %></td>
				                <td><%= offre.getSociete().getTelephone() %></td>
				                <td><%= offre.getStatut() %></td>
				                <td>
				                    <%
				                        if ("soumis".equals(offre.getStatut()) && isDemandeOwner) {
				                    %>
				                            <a href="mettreAJourStatutOffre?offre_id=<%= offre.getId() %>&statut=accepté&demande_id=<%= demande.getId() %>" class="btn btn-success">Accepter</a>
				                            <a href="mettreAJourStatutOffre?offre_id=<%= offre.getId() %>&statut=non_accepté&demande_id=<%= demande.getId() %>" class="btn btn-danger">Rejeter</a>
				                    <%
				                        }
				                    %>
				                </td>
				            </tr>
				<%
				        }
				    } else {
				%>
				        <tr>
				            <td colspan="3" class="text-center">Aucune offre trouvée.</td>
				        </tr>
				<%
				    }
				%>
            </tbody>
        </table>

        <div class="text-center mt-4">
            <a href=<%= utilisateurId != null ? "dashboardUser" : "demandes"  %> class="btn btn-primary">Retour</a>
        </div>
    </div>
</body>
</html>