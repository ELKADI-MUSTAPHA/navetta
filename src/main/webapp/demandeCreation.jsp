<%
	if(session.getAttribute("user_id")==null){
		response.sendRedirect("login.jsp");
	}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Créer une Demande</title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
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
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
	<div class="main">

		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Créer une demande</h2>
					
						<form method="post" action="createDemande" class="register-form"
							id="register-form">
							<div class="form-group">
								<label for="ville_depart"><i class="zmdi zmdi-pin"></i></label> 
								<input type="text" name="ville_depart" id="ville_depart" placeholder="Ville de Départ" required />
							</div>
							<div class="form-group">
								<label for="ville_arrivee"><i class="zmdi zmdi-pin"></i></label> 
								<input type="text" name="ville_arrivee" id="ville_arrivee" placeholder="Ville d'Arrivée" required />
							</div>
							<div class="form-group">
								<label for="heure_depart" style="top: 20% !important"><i class="zmdi zmdi-time"></i> Heure de Départ</label> 
								<input type="time" name="heure_depart" id="heure_depart" placeholder="Heure de Départ" required />
							</div>
							<div class="form-group">
								<label for="heure_arrivee" style="top: 20% !important"><i class="zmdi zmdi-time"></i> Heure d'Arrivée</label> 
								<input type="time" name="heure_arrivee" id="heure_arrivee" placeholder="Heure d'Arrivée" required />
							</div>
							<div class="form-group">
								<label for="periode_debut"><i class="zmdi zmdi-calendar"></i></label> 
								<input type="date" name="periode_debut" id="periode_debut" placeholder="Période de Début" required />
							</div>
							<div class="form-group">
								<label for="periode_fin"><i class="zmdi zmdi-calendar"></i></label> 
								<input type="date" name="periode_fin" id="periode_fin" placeholder="Période de Fin" required />
							</div>
							<div class="form-group">
								<label for="prix"><i class="zmdi zmdi-money"></i></label> 
								<input type="number" step="0.01" name="prix" placeholder="Prix" required>
							</div>
							<div class="form-group">
								<label for="description"><i class="zmdi zmdi-comment-text"></i></label> 
								<textarea name="description" id="description" placeholder="Description de l'autocar" rows="4" cols="30" required></textarea>
							</div>
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Créer la Navette" />
							</div>
						</form>
					</div>
					<div class="signup-image">
						<figure>
							<img src="images/signup-image.jpg" alt="sing up image">
						</figure>
						<a href="dashboardUser" class="signup-image-link">Retour au tableau de bord</a>
					</div>
				</div>
			</div>
		</section>
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