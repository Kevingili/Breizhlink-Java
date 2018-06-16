<%@include file="header.jsp" %>
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
	<p>Créer un compte pour voir nos autres options possibles</p>
	</div>
	</div>
		
    
<%String url = (String)request.getAttribute("url"); %>

<c:if test="${not empty url}">
  <hr>
   Lien raccourci : <a href="http://localhost:8080/test/link?id=<%=url%>">http://localhost:8080/test/link?id=<%=url%></a>
</c:if>
     
<%@include file="footer.jsp" %>