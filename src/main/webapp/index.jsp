<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="Navetta - Votre plateforme de réservation de navettes au Maroc" />
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
<style>
    /* Custom styles for responsiveness */
    .hero-section {
        background: url('assets/img/hero-bg.jpg') no-repeat center center/cover;
        height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
        text-align: center;
        color: white;
    }
    .hero-section h1 {
        font-size: 3.5rem;
        font-weight: bold;
        color: black;
    }
    .hero-section p {
        font-size: 1.5rem;
        margin-top: 20px;
        color: black;
    }
    .section-title {
        font-size: 2.5rem;
        font-weight: bold;
        margin-bottom: 40px;
        text-align: center;
    }
    .service-card {
        background: #ffffff;
        border-radius: 10px;
        padding: 20px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        transition: transform 0.3s ease;
    }
    .service-card:hover {
        transform: translateY(-10px);
    }
    .service-card img {
        width: 100%;
        border-radius: 10px;
    }
    .service-card h3 {
        font-size: 1.5rem;
        font-weight: bold;
        margin-top: 15px;
    }
    .service-card p {
        font-size: 1rem;
        color: #666;
    }
    .cta-section {
        background: #2c3e50;
        color: white;
        padding: 60px 0;
        text-align: center;
    }
    .cta-section h2 {
        font-size: 2.5rem;
        font-weight: bold;
        margin-bottom: 20px;
    }
    .cta-section p {
        font-size: 1.2rem;
        margin-bottom: 30px;
    }
    .cta-section .btn {
        font-size: 1.2rem;
        padding: 10px 30px;
    }
    .role-section {
        padding: 60px 0;
        text-align: center;
    }
    .role-section h2 {
        font-size: 2.5rem;
        font-weight: bold;
        margin-bottom: 40px;
    }
    .role-section .card {
        border: none;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        transition: transform 0.3s ease;
    }
    .role-section .card:hover {
        transform: translateY(-10px);
    }
    .role-section .card img {
        width: 100%;
        border-radius: 10px;
    }
    .role-section .card h3 {
        font-size: 1.5rem;
        font-weight: bold;
        margin-top: 15px;
    }
    .role-section .card p {
        font-size: 1rem;
        color: #666;
    }
</style>
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
	
    <!-- Hero Section -->
    <header class="hero-section">
        <div class="container">
            <h1>Bienvenue sur Navetta</h1>
            <p>Votre plateforme de réservation de navettes au Maroc</p>
            <a href="home" class="btn btn-primary btn-lg">Découvrir les offres</a>
        </div>
    </header>

    <!-- Role Section -->
    <section id="roles" class="role-section">
        <div class="container">
            <h2 class="section-title">Pour qui est Navetta ?</h2>
            <div class="row">
                <div class="col-md-6 mb-4">
                    <div class="card">
                        <img src="assets/img/user.jpg" alt="Utilisateurs">
                        <div class="card-body">
                            <h3>Utilisateurs</h3>
                            <p>Navetta vous permet de réserver facilement des navettes pour vos déplacements quotidiens ou occasionnels. Abonnez-vous à une navette ou créez une demande de transport personnalisée.</p>
                            <a href="login.jsp" class="btn btn-primary">Commencer</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 mb-4">
                    <div class="card">
                        <img src="assets/img/societe.jpg" alt="Sociétés">
                        <div class="card-body">
                            <h3>Sociétés de Transport</h3>
                            <p>Proposez vos offres de transport sur Navetta et atteignez des milliers d'utilisateurs. Gérez vos demandes et offrez des solutions de mobilité adaptées.</p>
                            <a href="loginCompany.jsp" class="btn btn-primary">Rejoindre Navetta</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Services Section -->
    <section id="services" class="py-5">
        <div class="container">
            <h2 class="section-title">Nos Services</h2>
            <div class="row">
                <div class="col-md-4 mb-4">
                    <div class="service-card">
                        <img src="assets/img/private2.jpg" alt="Navettes Privées">
                        <h3>Navettes Privées</h3>
                        <p>Réservez une navette privée pour vos déplacements personnels ou professionnels.</p>
                    </div>
                </div>
                <div class="col-md-4 mb-4">
                    <div class="service-card">
                        <img src="assets/img/shared.jpg" alt="Navettes Partagées">
                        <h3>Navettes Partagées</h3>
                        <p>Économisez avec nos navettes partagées, idéales pour les trajets réguliers.</p>
                    </div>
                </div>
                <div class="col-md-4 mb-4">
                    <div class="service-card">
                        <img src="assets/img/events.jpg" alt="Transport d'Événements">
                        <h3>Transport d'Événements</h3>
                        <p>Organisez le transport pour vos événements avec nos services sur mesure.</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Call to Action Section -->
    <section class="cta-section">
        <div class="container">
            <h2>Prêt à réserver votre navette ?</h2>
            <p>Rejoignez des milliers de clients satisfaits et profitez de nos services de transport de qualité.</p>
            <a href="login.jsp" class="btn btn-light btn-lg">Commencer maintenant</a>
        </div>
    </section>

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