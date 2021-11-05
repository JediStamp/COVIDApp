<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>COVID App</title>
<link rel="stylesheet" href="http://localhost:8080/COVID_App/html/css/style.css">
</head>

<body>
<h1 class="centered">Enter Email Page</h1>
<hr>

<div class="center-me">
	<div class="light">
	<form action="./EmailServlet" method="post">
	<table>
		<tr>
			<th colspan=2>Login</th>
		</tr>
		
		<tr>
			<td><label for="email">Email:</label></td>
			<td><input type="email" id="email" name="email" size="30"
				required="required"/></td>
		</tr>
						
		<tr>
			<td class="centered" colspan=2><input type="submit" value="Send Email"></td>
		</tr>
		
	</table>
</form>
</div>
</div>

</body>
</html>