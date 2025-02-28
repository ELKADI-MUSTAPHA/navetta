<%@page import="com.projet.dao.CommentaireDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.projet.model.Navette" %>
<%@ page import="com.projet.model.Societe" %>
<%@ page import="com.projet.model.Utilisateur" %>
<%@ page import="com.projet.model.Abonnement" %>
<%@ page import="java.util.List" %>
<%@ page import="com.projet.model.Commentaire" %>
<%@ page import="java.text.SimpleDateFormat, java.sql.Time" %>



<html>
<head>
    <title>Détails de la Navette</title>
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
    </style>
</head>
<body>
	<div class="container">
	    <h2 class="text-center">Détails de la Navette</h2>
	    <%
	        Navette navette = (Navette) request.getAttribute("navette");
	        Societe societe = (Societe) request.getAttribute("societe");
	        List<Utilisateur> utilisateursAbonnes = (List<Utilisateur>) request.getAttribute("utilisateursAbonnes");
	        boolean isSociete = (Boolean) request.getAttribute("isSociete");
	        boolean isProprietaire = (Boolean) request.getAttribute("isProprietaire");
	        List<Commentaire> commentaires = (List<Commentaire>) request.getAttribute("commentaires");

	        if (navette != null && societe != null) {
	            int totalRatings = 0;
	            int numberOfRatings = 0;
	            for (Commentaire commentaire : commentaires) {
	                totalRatings += commentaire.getRating();
	                numberOfRatings++;
	            }
	            double averageRating = (numberOfRatings > 0) ? (double) totalRatings / numberOfRatings : 0;

		    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm"); // Hours and minutes only
		    Time heureDepart = Time.valueOf(navette.getHeureDepart()); // Convert String to Time
		    Time heureArrivee = Time.valueOf(navette.getHeureArrivee()); // Convert String to Time
		%>
		<!-- Table to display shuttle details -->
		<div class="table-responsive">
		    <table class="table table-bordered table-striped mt-4">
		        <tbody>
		            <tr>
		                <th>Trajet</th>
		                <td><%= navette.getVilleDepart() %> à <%= navette.getVilleArrivee() %></td>
		            </tr>
					<tr>
					    <th>Heure de trajet</th>
					    <td><%= timeFormat.format(heureDepart) %> à <%= timeFormat.format(heureArrivee) %></td>
					</tr>
		            <tr>
		                <th>Période</th>
		                <td><%= navette.getPeriodeDebut() %> / <%= navette.getPeriodeFin() %></td>
		            </tr>
		            <tr>
		                <th>Description</th>
		                <td><%= navette.getDescription() %></td>
		            </tr>
		            <tr>
		                <th>Nombre d'Abonnés</th>
		                <td><%= navette.getNombreAbonnes() %></td>
		            </tr>
		            <tr>
		                <th>Places Disponibles</th>
		                <td><%= navette.getPlacesDisponibles() %></td>
		            </tr>
		            <tr>
		                <th>Prix</th>
		                <td><strong><%= navette.getPrix() %>DH</strong></td>
		            </tr>
		            		            <tr>
		                <th>Email de la Société</th>
		                <td><%= societe.getEmail() %></td>
		            </tr>
		            <tr>
		                <th>Téléphone de la Société</th>
		                <td><%= societe.getTelephone() %></td>
		            </tr>
		            <tr class="table-warning">
		                <th><%= societe.getNom().toUpperCase() %></th>
		                <td>
		                    <% for (int i = 1; i <= (int) averageRating; i++) { %> ★ <% } %>
		                    <% for (int i = (int) averageRating; i < 5; i++) { %> ☆ <% } %>
		                    (<%= String.format("%.1f", averageRating) %>/5)
		                </td>
		            </tr>
		        </tbody>
		    </table>
		</div>
	            <%
	                if (isProprietaire && utilisateursAbonnes != null && !utilisateursAbonnes.isEmpty()) {
	            %>
	                    <h3 class="mt-4">Utilisateurs Abonnés</h3>
	                    <table class="table">
	                        <thead>
	                            <tr>
	                                <th>Nom</th>
	                                <th>Email</th>
	                                <th>Telephone</th>
	                                <th>Note Moyenne</th>
	                                <th>Action</th>
	                            </tr>
	                        </thead>
							<tbody>
							<%
							    List<Abonnement> abonnements = (List<Abonnement>) request.getAttribute("abonnements");
								CommentaireDAO commentaireDAO = new CommentaireDAO(); // Fetch ratings
								
								for (Abonnement abonnement : abonnements) { 
						            double avgRating = commentaireDAO.getAverageRating(abonnement.getUtilisateur().getId());
						            boolean alreadyRated = commentaireDAO.hasCompanyRatedUser((int) session.getAttribute("societe_id"), abonnement.getUtilisateur().getId());
								%>
							        <tr>
							            <td><%= abonnement.getUtilisateur().getNom() %></td>
							            <td><%= abonnement.getUtilisateur().getEmail() %></td>
							            <td><%= abonnement.getUtilisateur().getTelephone() %></td>
							            <td>
							                <% for (int i = 1; i <= (int) avgRating; i++) { %> ★ <% } %>
							                <% for (int i = (int) avgRating; i < 5; i++) { %> ☆ <% } %>
							                (<%= String.format("%.1f", avgRating) %>/5)
							            </td>
							            <td>
							                <% if (!alreadyRated) { %>
							                    <form action="RateUserServlet" method="post" class="d-inline">
							                        <input type="hidden" name="utilisateurId" value="<%= abonnement.getUtilisateur().getId() %>">
							                        <input type="hidden" name="navette_id" value="<%= navette.getId() %>">
							                        <select name="rating" class="form-select d-inline w-auto">
							                            <option value="1">★☆☆☆☆</option>
							                            <option value="2">★★☆☆☆</option>
							                            <option value="3">★★★☆☆</option>
							                            <option value="4">★★★★☆</option>
							                            <option value="5">★★★★★</option>
							                        </select>
							                        <button type="submit" class="btn btn-primary">Évaluer</button>
							                    </form>
							                <% } else { %>
							                    <span class="text-muted">Déjà évalué</span>
							                <% } %>
							            </td>
							            <td>
							                <% if ("CONFIRME".equals(abonnement.getStatus())) { %>
							                    <span class="text-success">✔ Confirmé</span>
							                <% } else { %>
							                    <span class="text-warning">🕓 En Attente</span>
							                <% } %>
							            </td>
							            <td>
							                <% if ("EN_ATTENTE".equals(abonnement.getStatus())) { %>
							                    <a href="ConfirmerAbonnementServlet?id=<%= abonnement.getId() %>&navette_id=<%= navette.getId() %>" class="btn btn-success">Confirmer</a>
							                <% } %>
							            </td>
							        </tr>
							    <% } %>
							</tbody>
	                    </table>
	            <%
	                } else if (isSociete && !isProprietaire) {
	            %>
	                    <p class="text-warning mt-4">Vous n'êtes pas autorisé à voir les utilisateurs abonnés à cette navette.</p>
	            <%
	                }
	            %>
	    <%
	        } else {
	    %>
	            <p class="text-danger text-center">Aucune donnée trouvée.</p>
	    <%
	        }
	    %>
        <div class="text-center mt-4">
            <a href=<%= session.getAttribute("societe_id") != null ? "dashboardSociete" : session.getAttribute("user_id") != null ? "dashboardUser" : "home"  %> class="btn btn-primary">Retour</a>
        </div>
        

		<h3 class="mt-5">Avis et Commentaires</h3>
		
		<%
		    boolean canComment = (Boolean) request.getAttribute("canComment");
		%>
		
		<!-- Display existing comments -->
		<div class="mt-3">
		    <% if (commentaires != null && !commentaires.isEmpty()) { %>
		        <% for (Commentaire commentaire : commentaires) { %>
		            <div class="border p-3 mb-2">
		                <strong><%= commentaire.getUtilisateur().getNom() %></strong> - 
		                
		                <!-- Display star rating -->
		                <span class="text-warning">
		                    <% for (int i = 1; i <= commentaire.getRating(); i++) { %>
		                        ★
		                    <% } %>
		                    <% for (int i = commentaire.getRating(); i < 5; i++) { %>
		                        ☆
		                    <% } %>
		                </span>
		
		                <p><%= commentaire.getComment() %></p>
		                <small class="text-muted"><%= commentaire.getDateCreation() %></small>
		            </div>
		        <% } %>
		    <% } else { %>
		        <p class="text-muted">Aucun commentaire pour cette société.</p>
		    <% } %>
		</div>

		
		<!-- Show comment form if user has a confirmed navette -->
		<% if (canComment) { %>
		    <h4 class="mt-4">Laisser un Commentaire</h4>
		    <form action="AjouterCommentaireServlet" method="post">
		        <input type="hidden" name="societe_id" value="<%= societe.getId() %>">
		        <input type="hidden" name="navette_id" value="<%= navette.getId() %>">
		        <label>Note (1-5) :</label>
		        <select name="rating" class="form-control mb-2" required>
		            <option value="5">★★★★★</option>
		            <option value="4">★★★★☆</option>
		            <option value="3">★★★☆☆</option>
		            <option value="2">★★☆☆☆</option>
		            <option value="1">★☆☆☆☆</option>
		        </select>
		        <label>Commentaire :</label>
		        <textarea name="comment" class="form-control mb-2" required></textarea>
		        <button type="submit" class="btn btn-primary">Envoyer</button>
		    </form>
		<% } else { %>
		    <p class="text-danger">Vous devez avoir une navette confirmée pour laisser un commentaire.</p>
		<% } %>
		        
	</div>
</body>
</html>