<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Accès restreint</title>
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
          <span class="form-inline mt-2 mt-md-0">
           <!-- <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>  --> 
            <a href="deconnexion"><img height="30" width="30" src="images/logout2.png"/></a>
          </span>
        </div>
      </nav>
    		</header>
    		<main role="main" class="container">
    		<h1>Acces restreint</h1>
        <p>Compte :  ${sessionScope.sessionUtilisateur.email}</p>
        <hr>
        <form action="ajout" method="post">
		
			<div class="form-group">
				<label for="email">Email</label>
				<input type="text" name="email" id="email" class="form-control">
			</div>
	
			<div class="form-group">
				<label for="dateD">Mot de passe</label>
				<input type="text" name="mdp" id="mdp" class="form-control">
			</div>
			
			<input type="submit" name="valider" class="btn btn-primary" value="Ajouter">
		</form>
        <hr>
        <ul>
        <c:forEach items="${ messages }" var="message" varStatus="boucle">
            <li>${ message }</li>
        </c:forEach>
        </ul>
        
        </main>
    </body>
</html>