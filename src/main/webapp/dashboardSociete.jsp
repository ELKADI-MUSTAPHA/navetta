<!-- add session validation here -->
<%
	if(session.getAttribute("societe_id")==null){
		response.sendRedirect("loginCompany.jsp");
	}
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.projet.model.Navette" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.text.SimpleDateFormat, java.sql.Time" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Dashboard Societe</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/index-styles.css" rel="stylesheet" />
</head>
<body id="page-top">
	<!-- Navigation-->
	<nav
		class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="/navette/index.jsp">Navetta</a>
			<button
				class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded"
				type="button" data-bs-toggle="collapse"
				data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				    <ul class="navbar-nav ms-auto">
				        <li class="nav-item mx-0 mx-lg-1">
				            <a class="nav-link py-3 px-0 px-lg-3 rounded" href="home">Navettes</a>
				        </li>
				        <%
			        		Integer societeId = (Integer) session.getAttribute("societe_id");
		                    if (societeId != null) {
		                %>
   				        <li class="nav-item mx-0 mx-lg-1">
				            <a class="nav-link py-3 px-0 px-lg-3 rounded" href="demandes">Demandes</a>
				        </li>
		                <%
		                    }
		                %>
		                
				        <% 
				            String userName = (String) session.getAttribute("nom"); 
				            if (userName != null || societeId != null) {
				        %>
				            <li class="nav-item mx-0 mx-lg-1">
				                <a class="nav-link py-3 px-0 px-lg-3 rounded" href="logout">Logout</a>
				            </li>
				            <li class="nav-item mx-0 mx-lg-1">
				                <%
				                    if (societeId != null) {
				                        // Si societe_id est présent, c'est une société
				                %>
				                    <a class="nav-link py-3 px-0 px-lg-3 rounded" href="dashboardSociete"><%= userName %>/Dashboard</a>
				                <%
				                    } else {
				                        // Sinon, c'est un utilisateur normal
				                %>
				                    <a class="nav-link py-3 px-0 px-lg-3 rounded" href="profile"><%= userName %>/Profile</a>
				                <%
				                    }
				                %>
				            </li>
				        <% 
				            } else { 
				        %>
				            <li class="nav-item mx-0 mx-lg-1">
				                <a class="nav-link py-3 px-0 px-lg-3 rounded" href="login.jsp">Login</a>
				            </li>
				            <li class="nav-item mx-0 mx-lg-1">
				                <a class="nav-link py-3 px-0 px-lg-3 rounded" href="loginCompany.jsp">Compte Societe</a>
				            </li>
				        <% 
				            } 
				        %>
				    </ul>
				</div>

		</div>
	</nav>
	<div class="container vh-100" style="margin-top: 10rem">
	    <div class="d-flex justify-content-between">
	        <h2>Liste des Navettes</h2>
	        <a href="navetteCreation.jsp" class="btn btn-primary">Créer une navette</a>
	    </div>
	    <table class="table">
	        <thead>
	            <tr>
	                <th>Trajet</th>
	                <th>Heure de trajet</th>
	                <th>Période</th>
	                <th>Prix</th>
	                <th>Places Disponibles</th>
	                <th>Action</th>
	            </tr>
	        </thead>
	        <tbody>
	            <%
	                List<Navette> navettes = (List<Navette>) request.getAttribute("navettes");
	                if (navettes != null && !navettes.isEmpty()) {
	                    for (Navette navette : navettes) {
	                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	                        LocalDate periodeFin = LocalDate.parse(navette.getPeriodeFin(), formatter);
	                        boolean isExpired = periodeFin.isBefore(LocalDate.now());
	                        
	            		    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm"); // Hours and minutes only
	            		    Time heureDepart = Time.valueOf(navette.getHeureDepart()); // Convert String to Time
	            		    Time heureArrivee = Time.valueOf(navette.getHeureArrivee()); // Convert String to Time
	            %>
	                        <tr onclick="window.location='detailsNavette?id=<%= navette.getId() %>';" style="cursor: pointer;" class="<%= isExpired ? "table-secondary" : "" %>">
	                            <td><%= navette.getVilleDepart() %> à <%= navette.getVilleArrivee() %></td>
	                            <td><%= timeFormat.format(heureDepart) %> à <%= timeFormat.format(heureArrivee) %></td>
	                            <td><%= navette.getPeriodeDebut() %> - <%= navette.getPeriodeFin() %></td>
	                            <td><%= navette.getPrix() %>DH</td>
	                            <td><%= navette.getPlacesDisponibles() %></td>
	                            <td>
	                                <%
	                                    if (isExpired) {
	                                %>
	                                        <span class="text-muted">Archivé</span>
	                                <%
	                                    } else if (navette.getPlacesDisponibles() > 0) {
	                                %>
	                                        <span class="text-success">Active</span>
	                                <%
	                                    } else {
	                                %>
	                                        <span class="text-danger">Complet</span>
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
	                        <td colspan="8" class="text-center">Aucune donnée trouvée.</td>
	                    </tr>
	            <%
	                }
	            %>
	        </tbody>
	    </table>
	</div>

	<!-- Footer-->
	<footer class="footer text-center" style="background-color: #2c3e50; color: #ffffff;">
	    <div class="container">
	        <div class="row">
	            <!-- Footer Location-->
	            <div class="col-lg-4 mb-5 mb-lg-0">
	                <h4 class="text-uppercase mb-4">Adresse</h4>
	                <p class="lead mb-0">
	                    Navetta Maroc <br />
	                    Rue Mohammed V, Casablanca <br />
	                    Maroc
	                </p>
	            </div>
	            <!-- Footer Social Icons-->
	            <div class="col-lg-4 mb-5 mb-lg-0">
	                <h4 class="text-uppercase mb-4">Suivez-nous</h4>
	                <a class="btn btn-outline-light btn-social mx-1" href="#!">
	                    <i class="fab fa-fw fa-facebook-f"></i>
	                </a>
	                <a class="btn btn-outline-light btn-social mx-1" href="#!">
	                    <i class="fab fa-fw fa-twitter"></i>
	                </a>
	                <a class="btn btn-outline-light btn-social mx-1" href="#!">
	                    <i class="fab fa-fw fa-instagram"></i>
	                </a>
	                <a class="btn btn-outline-light btn-social mx-1" href="#!">
	                    <i class="fab fa-fw fa-linkedin-in"></i>
	                </a>
	            </div>
	            <!-- Footer About Text-->
	            <div class="col-lg-4">
	                <h4 class="text-uppercase mb-4">À propos de Navetta</h4>
	                <p class="lead mb-0">
	                    Navetta est une plateforme de réservation de navettes au Maroc, conçue pour simplifier vos déplacements.
	                    <br />
	                    <a href="/" style="color: #18bc9c;">Découvrez nos services</a>.
	                </p>
	            </div>
	        </div>
	    </div>
	</footer>
	
	<!-- Copyright Section-->
	<div class="copyright py-4 text-center text-white" style="background-color: #1a252f;">
	    <div class="container">
	        <small>Copyright &copy; Navetta 2023. Tous droits réservés.</small>
	    </div>
	</div>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>
