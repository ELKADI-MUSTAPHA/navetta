<!-- add session validation here -->
<%@page import="com.projet.model.Abonnement"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.projet.model.Navette" %>
<%@ page import="java.util.List" %>
<%@ page import="com.projet.dao.AbonnementDAO" %>
<%@ page import="java.text.SimpleDateFormat, java.sql.Time" %>
<%@ page import="java.util.Set, java.util.HashSet" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Navette</title>
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
				                    <a class="nav-link py-3 px-0 px-lg-3 rounded" href="dashboardUser"><%= userName %>/Profile</a>
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

	
	<section class="page-section portfolio" id="portfolio">
    <div class="container min-vh-100" style="margin-top: 4rem">
        <h2 class="text-center mb-4">Liste des Navettes</h2>

        <!-- ✅ Filter Section -->
        <div class="row mb-4">
            <div class="col-md-5">
                <label class="form-label">De :</label>
                <select id="fromCity" class="form-select">
                    <option value="">Toutes les villes</option>
                    <% 
                    List<Navette> navettes = (List<Navette>) request.getAttribute("navettes");
                        Set<String> villesDepart = new HashSet<>();
                        for (Navette navette : navettes) { villesDepart.add(navette.getVilleDepart()); }
                        for (String ville : villesDepart) { 
                    %>
                        <option value="<%= ville %>"><%= ville %></option>
                    <% } %>
                </select>
            </div>
            <div class="col-md-5">
                <label class="form-label">À :</label>
                <select id="toCity" class="form-select">
                    <option value="">Toutes les villes</option>
                    <% 
                        Set<String> villesArrivee = new HashSet<>();
                        for (Navette navette : navettes) { villesArrivee.add(navette.getVilleArrivee()); }
                        for (String ville : villesArrivee) { 
                    %>
                        <option value="<%= ville %>"><%= ville %></option>
                    <% } %>
                </select>
            </div>
            <div class="col-md-2 d-flex align-items-end">
                <button id="resetFilter" class="btn btn-secondary w-100">Réinitialiser</button>
            </div>
        </div>

        <!-- ✅ Navettes Grid -->
        <div class="row" id="navetteContainer">
            <%
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		        // Récupérer l'ID de l'utilisateur connecté
		        Integer utilisateurId = (Integer) session.getAttribute("user_id");
		        
		        // Vérifier si l'utilisateur est déjà abonné et obtenir le statut
		        AbonnementDAO abonnementDAO = new AbonnementDAO();
		        
                for (Navette navette : navettes) {
                    Time heureDepart = Time.valueOf(navette.getHeureDepart());
                    Time heureArrivee = Time.valueOf(navette.getHeureArrivee());
                    String abonnementStatus = (utilisateurId != null) ? abonnementDAO.getAbonnementStatus(utilisateurId, navette.getId()) : null;
            %>
            <div class="col-md-6 col-lg-4 navette-card" 
                 data-from="<%= navette.getVilleDepart() %>" 
                 data-to="<%= navette.getVilleArrivee() %>"
                 onclick="window.location='detailsNavette?id=<%= navette.getId() %>';" style="cursor: pointer;">
                <div class="card shadow-lg border-0 mb-4">
                    <div class="card-body">
                        <h5 class="card-title"><%= navette.getVilleDepart() %> → <%= navette.getVilleArrivee() %></h5>
                        <p class="card-text">
                            <strong>Heure:</strong> <%= timeFormat.format(heureDepart) %> - <%= timeFormat.format(heureArrivee) %> <br>
                            <strong>Période:</strong> <%= navette.getPeriodeDebut() %> - <%= navette.getPeriodeFin() %> <br>
                            <strong>Prix:</strong> <%= navette.getPrix() %> DH <br>
                            <strong>Places Disponibles:</strong> 
                            <span class="<%= navette.getPlacesDisponibles() > 0 ? "text-success" : "text-danger" %>">
                                <%= navette.getPlacesDisponibles() %>
                            </span>
                        </p>

                        <div class="text-center">
                            <% if (abonnementStatus != null) { %>
                                <% if ("CONFIRME".equals(abonnementStatus)) { %>
                                    <span class="badge bg-success p-2">✔ Confirmé</span>
                                <% } else { %>
                                    <a href="desabonner?navette_id=<%= navette.getId() %>" class="btn btn-danger btn-sm">Se désabonner</a>
                                <% } %>
                            <% } else if (navette.getPlacesDisponibles() > 0) { %>
                                <a href="abonner?navette_id=<%= navette.getId() %>" class="btn btn-primary btn-sm">S'abonner</a>
                            <% } else { %>
                                <span class="badge bg-danger p-2">Complet</span>
                            <% } %>
                        </div>
                    </div>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
</section>

<!-- ✅ JavaScript for Filtering (No Refresh) -->
<script>
document.addEventListener("DOMContentLoaded", function () {
    const fromCitySelect = document.getElementById("fromCity");
    const toCitySelect = document.getElementById("toCity");
    const resetButton = document.getElementById("resetFilter");
    const navetteCards = document.querySelectorAll(".navette-card");

    function filterNavettes() {
        const fromCity = fromCitySelect.value.toLowerCase();
        const toCity = toCitySelect.value.toLowerCase();

        navetteCards.forEach(card => {
            const cardFrom = card.getAttribute("data-from").toLowerCase();
            const cardTo = card.getAttribute("data-to").toLowerCase();
            
            const matchesFrom = fromCity === "" || cardFrom.includes(fromCity);
            const matchesTo = toCity === "" || cardTo.includes(toCity);

            if (matchesFrom && matchesTo) {
                card.style.display = "block";
            } else {
                card.style.display = "none";
            }
        });
    }

    fromCitySelect.addEventListener("change", filterNavettes);
    toCitySelect.addEventListener("change", filterNavettes);
    resetButton.addEventListener("click", () => {
        fromCitySelect.value = "";
        toCitySelect.value = "";
        filterNavettes();
    });
});
</script>
	
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
