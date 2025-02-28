<!-- add session validation here -->
<%
	if(session.getAttribute("societe_id")==null){
		response.sendRedirect("loginCompany.jsp");
	}
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.projet.model.Demande" %>
<%@ page import="java.util.List" %>
<%@page import="com.projet.dao.OffreDAO"%>
<%@page import="com.projet.dao.DemandeDAO"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Demandes</title>
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
    <div class="container vh-100" style="margin-top: 3rem">
        <h2>Liste des Demandes</h2>
        
                <!-- Search Filter -->
        <div class="row mb-4">
            <div class="col-md-5">
                <input type="text" id="searchDepart" class="form-control" placeholder="Ville de Départ">
            </div>
            <div class="col-md-5">
                <input type="text" id="searchArrivee" class="form-control" placeholder="Ville d'Arrivée">
            </div>
            <div class="col-md-2">
                <button class="btn btn-primary w-100" onclick="filterDemandes()">Rechercher</button>
            </div>
        </div>
        
        
        <div class="row">
            <% List<Demande> demandes = (List<Demande>) request.getAttribute("demandes");
		    // Instancier DemandeDAO et OffreDAO
		    DemandeDAO demandeDAO = new DemandeDAO();
		    OffreDAO offreDAO = new OffreDAO();
               if (demandes != null && !demandes.isEmpty()) {
                   for (Demande demande : demandes) {
                       boolean hasAcceptedOffer = demandeDAO.hasAcceptedOffer(demande.getId());
                       boolean isAcceptedOfferBySociete = (societeId != null) && offreDAO.isAcceptedOfferBySociete(demande.getId(), societeId);
                       boolean hasSubmittedOffer = (societeId != null) && offreDAO.hasSubmittedOffer(societeId, demande.getId());
            %>
            <div class="col-md-4">
                <div class="card mb-4 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title"><%= demande.getVilleDepart() %> → <%= demande.getVilleArrivee() %></h5>
                        <p class="card-text">
                            <strong>Départ :</strong> <%= demande.getHeureDepart() %><br>
                            <strong>Arrivée :</strong> <%= demande.getHeureArrivee() %><br>
                            <strong>Période :</strong> <%= demande.getPeriodeDebut() %> - <%= demande.getPeriodeFin() %><br>
                            <strong>Prix :</strong> <%= demande.getPrix() %> DH
                        </p>
                        <% if (hasAcceptedOffer) { %>
                            <% if (isAcceptedOfferBySociete) { %>
                                <span class="badge bg-success">Offre acceptée</span>
                            <% } else { %>
                                <span class="badge bg-warning">Offre non acceptée</span>
                            <% } %>
                        <% } else { %>
                            <% if (hasSubmittedOffer) { %>
                                <span class="badge bg-success">Offre soumise</span>
                            <% } else { %>
                                <a href="soumettreOffre?demande_id=<%= demande.getId() %>" class="btn btn-success">Soumettre une offre</a>
                            <% } %>
                        <% } %>
                    </div>
                </div>
            </div>
            <% } } else { %>
            <div class="col-12 text-center">
                <p>Aucune demande trouvée.</p>
            </div>
            <% } %>
        </div>
    </div>
</section>

<script>
    function filterDemandes() {
        let depart = document.getElementById('searchDepart').value.toLowerCase();
        let arrivee = document.getElementById('searchArrivee').value.toLowerCase();
        
        document.querySelectorAll('.demande-card').forEach(card => {
            let cardDepart = card.getAttribute('data-depart');
            let cardArrivee = card.getAttribute('data-arrivee');
            
            if ((depart === '' || cardDepart.includes(depart)) && (arrivee === '' || cardArrivee.includes(arrivee))) {
                card.style.display = 'block';
            } else {
                card.style.display = 'none';
            }
        });
    }
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
