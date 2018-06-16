<%@include file="../header.jsp" %>
    <h1>Création d'un compte</h1>
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
<%@include file="../footer.jsp" %>