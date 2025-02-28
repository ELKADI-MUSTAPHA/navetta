<%
	if(session.getAttribute("societe_id")==null){
		response.sendRedirect("loginCompany.jsp");
	}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Cr�er une Navette</title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
	<div class="main">

		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Cr�er une Navette</h2>
					
						<form method="post" action="createNavette" class="register-form"
							id="register-form">
							<div class="form-group">
								<label for="ville_depart"><i class="zmdi zmdi-pin"></i></label> 
								<input type="text" name="ville_depart" id="ville_depart" placeholder="Ville de D�part" required />
							</div>
							<div class="form-group">
								<label for="ville_arrivee"><i class="zmdi zmdi-pin"></i></label> 
								<input type="text" name="ville_arrivee" id="ville_arrivee" placeholder="Ville d'Arriv�e" required />
							</div>
							<div class="form-group">
								<label for="heure_depart" style="top: 20% !important"><i class="zmdi zmdi-time"></i> Heure de D�part</label> 
								<input type="time" name="heure_depart" id="heure_depart" placeholder="Heure de D�part" required />
							</div>
							<div class="form-group">
								<label for="heure_arrivee" style="top: 20% !important"><i class="zmdi zmdi-time"></i> Heure d'Arriv�e</label> 
								<input type="time" name="heure_arrivee" id="heure_arrivee" placeholder="Heure d'Arriv�e" required />
							</div>
							<div class="form-group">
								<label for="periode_debut"><i class="zmdi zmdi-calendar"></i></label> 
								<input type="date" name="periode_debut" id="periode_debut" placeholder="P�riode de D�but" required />
							</div>
							<div class="form-group">
								<label for="periode_fin"><i class="zmdi zmdi-calendar"></i></label> 
								<input type="date" name="periode_fin" id="periode_fin" placeholder="P�riode de Fin" required />
							</div>
							<div class="form-group">
								<label for="prix"><i class="zmdi zmdi-money"></i></label> 
								<input type="number" step="0.01" name="prix" placeholder="Prix" required>
							</div>							
							<div class="form-group">
								<label for="description"><i class="zmdi zmdi-comment-text"></i></label> 
								<textarea name="description" id="description" placeholder="Description de l'autocar" rows="4" cols="30" required></textarea>
							</div>
							<div class="form-group">
								<label for="nombre_abonnes"><i class="zmdi zmdi-accounts"></i></label> 
								<input type="number" name="nombre_abonnes" id="nombre_abonnes" placeholder="Nombre d'Abonn�s" required />
							</div>
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Cr�er la Navette" />
							</div>
						</form>
					</div>
					<div class="signup-image">
						<figure>
							<img src="images/signup-image.jpg" alt="sing up image">
						</figure>
						<a href="dashboardSociete.jsp" class="signup-image-link">Retour au tableau de bord</a>
					</div>
				</div>
			</div>
		</section>
	</div>

	<!-- JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="js/main.js"></script>
	
	<script type="text/javascript">
		var status = document.getElementById("status").value;
		if (status === "success") {
			alert("Navette cr��e avec succ�s !");
		} else if (status === "failed") {
			alert("Erreur lors de la cr�ation de la navette.");
		}
	</script>
</body>
</html>