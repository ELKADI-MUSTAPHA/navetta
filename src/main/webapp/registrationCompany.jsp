<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Sign Up Form by Colorlib</title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->

<link rel="stylesheet" href="css/style.css">
</head>
<body>
<input type="hidden" value="<%= request.getAttribute("status") %>">
	<div class="main">

		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Créer un compte Societe</h2>
					
						<form method="post" action="registerCompany" class="register-form"
							id="register-form">
							<div class="form-group">
								<label for="societe_nom"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="societe_nom" id="societe_nom" placeholder="Nom de Societe" />
							</div>
							<div class="form-group">
								<label for="societe_email"><i class="zmdi zmdi-email"></i></label> <input
									type="email" name="societe_email" id="societe_email" placeholder="Societe Email" />
							</div>
							<div class="form-group">
								<label for="telephone"><i class="zmdi zmdi-email"></i></label> <input
									type="tel" name="telephone" id="telephone" placeholder="Votre Telephone" />
							</div>							
							<div class="form-group">
								<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="mot_de_passe" id="mot_de_passe" placeholder="Mot de passe" />
							</div>
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Register" />
									<a href="loginCompany.jsp" class="form-submit reg">Je suis un membre</a>
							</div>
						</form>
					</div>
					<div class="signup-image">
						<figure>
							<img src="images/signup-image.jpg" alt="sing up image">
						</figure>
						
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
		if( status == "success" ){
			console.log()
		}
	</script>
</body>
</html>