<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1 class="text-center">Ajouter une visiste</h1>
		
		<form action="visite" method="post">
		
			<div class="form-group">
				<label for="nomEtudiant">NomEtudiant</label>
				<input type="text" name="nomEtudiant" id="nomEtudiant" class="form-control">
			</div>
	
			<div class="form-group">
				<label for="dateD">Date</label>
				<input type="text" name="dateD" id="dateD" class="form-control">
			</div>
	
			<div class="form-group">
				<label for="note">Note</label>
				<input type="number" name="note" id="note" class="form-control">
			</div>
			
			<input type="submit" name="valider" class="btn btn-primary">
		</form>
	</div>
	
</body>
</html>