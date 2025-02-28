<!-- add session validation here -->
<%
	if(session.getAttribute("societe_id")!=null){
		response.sendRedirect("index.jsp");
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Login Company</title>

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<div class="main">

		<!-- Sing in  Form -->
		<section class="sign-in">
			<div class="container">
				<div class="signin-content">
					<div class="signin-image">
						<figure>
							<img src="images/signin-image.jpg" alt="sing up image">
						</figure>
						
					</div>

					<div class="signin-form">
						<h2 class="form-title">S'identifier</h2>
						<form method="post" action="loginCompany" class="register-form"
							id="login-form">
							<div class="form-group">
								<label for="societe_email"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="email" name="societe_email" id="societe_email"
									placeholder="Email de Societe" />
							</div>
							<div class="form-group">
								<label for="password"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="mot_de_passe" id="mot_de_passe"
									placeholder="Password" />
							</div>
							<div class="form-group form-button">
								<input type="submit" name="signin" id="signin"
									class="form-submit" value="Log in" />
									<a href="registrationCompany.jsp" class="form-submit reg">Créer un compte societe</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</section>

	</div>

	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>