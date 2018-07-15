<%@include file="../header.jsp" %>
 <h1>Mon compte</h1>

 <c:if test="${ !empty liens}">
	<table class="table table-bordered">
	  <tr>
	    <th>URL breizhlink</th>
	    <th>URL complète</th>
	    <th>Date de création</th>
	    <th>Nombre de cliques</th>
	  </tr>
	  <c:forEach var="lien" items="${ liens }">
	  <tr>
	   <td>http://localhost:8080/test/link?id=<c:out value="${ lien.urlshort }" /> </td>
	    <td><c:out value="${ lien.urlfull }" /></td>
	    <td><c:out value="${ lien.datecreation }" /></td>
	    <td><c:out value="${ lien.nbclique }" /></td>
	</tr>
	</c:forEach>
	  
	</table>
</c:if>

<c:if test="${ empty liens}">
	<h3>Vous n'avez ajouter aucune URL</h3>
	<p>Vous pouvez en ajouter en <a href="http://localhost:8080/test/">cliquant ici</a></p>
</c:if>   


<%@include file="../footer.jsp" %>