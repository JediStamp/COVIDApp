<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>COVID App</title>
<link rel="stylesheet" href="http://localhost:8080/COVID_App/html/css/style.css">
</head>

<body>
<h1 class="centered">Login Page</h1>
<hr>

<div class="center-me">
	<div class="light left_side">
	<form action="./LoginServlet" method="post">
	<table>
		<tr>
			<th colspan=2>Login</th>
		</tr>
		
		<tr>
			<td><label for="email">Email:</label></td>
			<td><input type="email" id="email" name="email" size="50"
				required="required"/></td>
		</tr>
		
		<tr>
			<td><label for="pwd">Password:</label></td>
			<td><input type="password" id="pwd" name="pwd" size="50"
				required="required"/></td>
		</tr>
		
		<tr>
			<td colspan=2 class="right_align"><a href="#">Forgot My Password</a></td>
		</tr>
		
		<tr>
			<td class="centered" colspan=2><input type="submit" value="Login"></td>
		</tr>
		
	</table>
</form>
	</div> <!-- end div right_side -->
		<div class="dark right_side">
	<form action="./RegisterServlet" method="get">
	<table>
		<tr>
			<th>About the COVID App</th>
		</tr>
		
		<tr>
			<td>This is our introductory blurb about our app. Much text goes here.</td>
		</tr>

		<tr>
			<td class="centered">Don't have an account yet?</td>
		</tr>
		
		<tr>
			<td class="centered"><input type="submit" value="Register"></td>
		</tr>
		
	</table>
</form>
	</div> <!-- end div dark left_side -->
</div><!-- end div center_me -->

<div>
 <% if (null!=request.getAttribute("errorMsg"))
{
    %>	
	<h2 class="error_msg">
	<%
	out.println(request.getAttribute("errorMsg"));
	%>
	</h2>	
	<% }
	%>
</div>

</body>
</html>