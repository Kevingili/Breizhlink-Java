<%@include file="../header.jsp" %>
 <h1>Mon compte</h1>

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


<%@include file="../footer.jsp" %>