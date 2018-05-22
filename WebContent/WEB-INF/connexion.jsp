<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Connexion</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    		<style type="text/css">
    		body {
    			margin-top: 75px;
		}
    		</style>
    </head>
    <body>
    <header>
    		 <!-- Fixed navbar -->
      <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href="/test/"><img height="40" width="40" src="images/java.png"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
              <a class="nav-link" href="/test/">Home <span class="sr-only">(current)</span></a>
            </li>
          </ul>
     
        </div>
      </nav>
    		</header>
    		<main role="main" class="container">
    		<h1>Connexion</h1>
        <form method="post" action="connexion">

                <p>Vous pouvez vous connecter via ce formulaire.</p>
				
				<div class="row">
					<div class="col-md-2">
						<label for="nom">Adresse email <span class="requis">*</span></label>
					</div>
					<div class="col-md-4">
						<input class="form-control" type="email" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" />
					</div>
					<div class="col-md-4">
						<span class="erreur">${form.erreurs['email']}</span>
					</div>
				</div>
                
                <br>
                
                <div class="row">
					<div class="col-md-2">
						<label for="motdepasse">Mot de passe <span class="requis">*</span></label>
					</div>
					<div class="col-md-4">
						 <input class="form-control" type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
					</div>
					<div class="col-md-4">
						<span class="erreur">${form.erreurs['motdepasse']}</span>
					</div>
				</div>

				<br>
				
				<div class="row">
					<div class="col-md-2">
					</div>
					<div class="col-md-4">
						<input type="submit" class="btn btn-primary" value="Connexion" class="sansLabel" />
					</div>
				</div>

                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                
                <%-- Vérification de la présence d'un objet utilisateur en session --%>
                <c:if test="${!empty sessionScope.sessionUtilisateur}">
                    <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
                    <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUtilisateur.email}</p>
                </c:if>
        </form>
        </main>
    </body>
</html>