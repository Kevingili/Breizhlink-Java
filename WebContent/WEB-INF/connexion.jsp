<%@include file="../header.jsp" %>
    		<h1>Connexion</h1>
        <form method="post" action="connexion">

                <p>Vous pouvez vous connecter via ce formulaire.</p>
				
				<div class="row">
					<div class="col-md-2">
						<label for="nom">Adresse email <span class="requis"></span></label>
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
						<label for="motdepasse">Mot de passe <span class="requis"></span></label>
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
<%@include file="../footer.jsp" %>