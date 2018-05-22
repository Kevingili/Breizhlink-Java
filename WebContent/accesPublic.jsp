<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Accueil</title>
       <!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<style type="text/css">
    		body {
    			margin-top: 75px;
		}
    		</style>
    </head>
    <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Breizhlink</a>
        </div>
        
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="#">Accueil</a></li>
            <li><a href="#about">Présentation</a></li>
            <li><a href="#contact">Création d'un compte</a></li>
          </ul>
          <div class="navbar-form navbar-right">
            <div class="form-group">
              <input type="text" placeholder="Email" class="form-control">
            </div>
            <div class="form-group">
              <input type="password" placeholder="Password" class="form-control">
            </div>
            <button type="submit" class="btn btn-success">Sign in</button>
          </div>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>

    <div class="container">
      <!-- Example row of columns -->
      <form class="row" action="raccourcir" method="post">
      
	      <div class="col-md-2">
	      	<label>URL à raccourcir</label>
	      </div>
	      <div class="col-md-8">
	      	<input type="text" class="form-control" name="url">
	      </div>
	       <div class="col-md-2">
	      	<button class="btn btn-primary">Raccourcir</button>
	      </div>    	
      </form>

      <br>
      <div class="row">
      
	      <div class="col-md-2">
	      </div>
	      <div class="col-md-8">
	      		<div class="custom-control custom-checkbox">
		  			<input type="checkbox" class="custom-control-input" id="customCheck1">
		  			<label class="custom-control-label" for="customCheck1">Sécurisée avec mot de passe</label>
		  		</div>
	      </div> 	
      </div>
	<br>
	<div class="row">
		<div class="col-md-2">
	      </div>
	      <div class="col-md-8">
	<p>Créer un compte pour voir nos autres options possibles
	</p>
	</div>
	</div>
		
    
<%String url = (String)request.getAttribute("url"); %>

<c:if test="${not empty url}">
  <hr>
   Lien raccourci : <a href="http://localhost:8080/test/link?id=<%= url%>">http://localhost:8080/test/link/<%= url%></a>
</c:if>
      <hr>

      <footer>
        <p>Devenir partenaire - CGV - Mentions légales</p>
      </footer>
    </div> <!-- /container -->

  </body>
</html>