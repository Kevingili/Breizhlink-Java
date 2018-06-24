<%@include file="../header.jsp" %>
<h2>URL Demandé : http://localhost:8080/test/link?id=${url} </h2>
<form method="post" action="link" class="row">
<input type="text" name="url_short" value="${url}" style="display:none;">
<div class='col-md-2'>
	<label>Mot de passe : </label>
</div>
<div class='col-md-5'>
	<input type="text" name="password" class="form-control">
</div>
<div class='col-md-3'>
	<button class="btn btn-primary">Ouvrir</button>
</div>
</form>
<p>${mdpwrong}</p>


<% System.out.println("urllll ="+request.getAttribute("url")); %>

<%@include file="../footer.jsp" %>