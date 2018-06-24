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
	      
	           <div class="col-md-2">
	      </div>
	      <div class="col-md-8">
	       <c:if test="${ !empty sessionScope.sessionUtilisateur}">
            		<div class="custom-control custom-checkbox">
		  			<input type="checkbox" class="custom-control-input" id="customCheck1">
		  			<label class="custom-control-label" for="customCheck1">Sécurisée avec mot de passe</label>
		  		</div>
		  		<input type="text" class="form-control" name="mdpfield" id="mdpfield" placeholder="mot de passe pour le lien" style="display:none;">
    			</c:if>
	      		
	      </div> 	
      </form>

	<br>
	<div class="row">
		<div class="col-md-2">
	      </div>
	      <div class="col-md-8">
	        <c:if test="${ empty sessionScope.sessionUtilisateur}">
            		<p>Créer un compte pour voir nos autres options possibles</p>
    			</c:if>
		 </div>
	</div>

<script type="text/javascript">
$('.custom-checkbox').click(function(){
	if(document.getElementById('customCheck1').checked) {
	    $("#mdpfield").show();
	} else {
	    $("#mdpfield").hide();
	    $("#mdpfield").val("");
	}
})


</script>
    
<%String url = (String)request.getAttribute("url"); %>

<c:if test="${not empty url}">
  <hr>
   Lien raccourci : <a href="http://localhost:8080/test/link?id=<%=url%>">http://localhost:8080/test/link?id=<%=url%></a>
</c:if>
 
<%@include file="footer.jsp" %>