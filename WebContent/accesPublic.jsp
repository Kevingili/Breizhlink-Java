<%@include file="header.jsp" %>
      <!-- Example row of columns -->
      <form action="raccourcir" method="post">
      <div class="row">
      
	      <div class="col-md-2">
	      	<label>URL � raccourcir</label>
	      </div>
	      <div class="col-md-8">
	      	<input type="text" class="form-control" name="url" id="url">
	      </div>
	       <div class="col-md-2">
	      	<button id="bouton-envoi" class="btn btn-primary" style="display:none;">Raccourcir</button>
	      </div>    	
	      

	      </div>
	      <div class="row">
	       <div class="col-md-2">
	      	
	      </div>
	      <div class="col-md-8">
	       <c:if test="${ !empty sessionScope.sessionUtilisateur}">
            		
            		<div class="custom-control custom-checkbox">
		  			<input type="checkbox" class="custom-control-input" id="customCheck1">
		  			<label class="custom-control-label" for="customCheck1">S�curis�e avec mot de passe</label>
		  		</div>
		  		<input type="text" class="form-control" name="mdpfield" id="mdpfield" placeholder="mot de passe pour le lien" style="display:none;">
		  		
		  		<div class="custom-control custom-checkbox">
		  			<input type="checkbox" class="custom-control-input" id="customCheck2">
		  			<label class="custom-control-label" for="customCheck2">Nombre de clique max</label>
		  		</div>
		  		<input type="number" class="form-control" name="maxfield" id="maxfield" style="display:none;">
		  		
		  		<div class="custom-control custom-checkbox">
		  			<input type="checkbox" class="custom-control-input" id="customCheck3">
		  			<label class="custom-control-label" for="customCheck3">Expiration</label>
		  		</div>
		  		<input type="date" class="form-control" name="expiration" id="expiration" style="display:none;">
		  		
		  		
    			</c:if>
	      		
	      </div> 
	      </div>	
      </form>

	<br>
	<div class="row">
		<div class="col-md-2">
	      </div>
	      <div class="col-md-8">
	        <c:if test="${ empty sessionScope.sessionUtilisateur}">
            		<p>Cr�er un compte pour voir nos autres options possibles</p>
    			</c:if>
		 </div>
	</div>

<script type="text/javascript">
$( "#url" ).keypress(function() {
	$('#bouton-envoi').show();
});
	
$('.custom-checkbox').click(function(){
	if(document.getElementById('customCheck1').checked) {
	    $("#mdpfield").show();
	} else {
	    $("#mdpfield").hide();
	    $("#mdpfield").val("");
	}
	
	if(document.getElementById('customCheck2').checked) {
	    $("#maxfield").show();
	} else {
	    $("#maxfield").hide();
	    $("#maxfield").val("");
	}
	if(document.getElementById('customCheck3').checked) {
	    $("#expiration").show();
	} else {
	    $("#expiration").hide();
	    $("#expiration").val("");
	}
})


</script>
    
<%String url = (String)request.getAttribute("url"); %>

<c:if test="${not empty url}">
  <hr>
   Lien raccourci : <a href="http://localhost:8080/test/link?id=<%=url%>">http://localhost:8080/test/link?id=<%=url%></a>
</c:if>
 
<%@include file="footer.jsp" %>