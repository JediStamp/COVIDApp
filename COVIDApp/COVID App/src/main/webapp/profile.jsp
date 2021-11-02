<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>COVID App</title>
<link rel="stylesheet" href="http://localhost:8080/COVID_App/html/css/style.css">
</head>

<body>
<h1 class="centered">Profile Page</h1>
<!--  This is where the code for navigation bar will go  -->
	<div class="menubar">
		<a href="profile.jsp">PROFILE</a>
		<a href="questionnaire.jsp">QUESTIONNAIRE</a>
		<a href="">RESULTS</a>
	</div>
<hr>

<div class="center-me">
	<div class="light left_side">
	<form action="./ProfileServlet" method="post">
	<table>
		<tr>
			<th colspan=2>Profile</th>
		</tr>
		
		<tr>
			<td><label for="first_name">First Name:</label></td>
			<td><input type="text" id="first_name" name="first_name"/></td>
		</tr>
		
		<tr>
			<td><label for="last_name">Last Name:</label></td>
			<td><input type="text" id="last_name" name="last_name"/></td>
		</tr>
		
		<tr>
			<td><label for="email">Email:</label></td>
			<td><input type="email" id="email" name="email"/></td>
		</tr>
		
		<tr>
			<td><label for="pwd">Password:</label></td>
			<td><input type="password" id="pwd" name="pwd"/></td>
		</tr>
		
		<tr>
			<td class="centered" colspan=2><input type="submit" value="Update"></td>
		</tr>
		
	</table>
	</form>
	</div> <!-- end div dark left_side -->
	
	<div class="dark right_side">
	<form action="./ProfileServlet" method="get">
	<table>
		<tr>
			<th>PROFILE PIC GOES HERE</th>
		</tr>
		
		<tr>
			<td><label for="owner">Team Owner</label></td>
			<td><input type="checkbox" id="owner" name="owner"/></td>
		</tr>
	</table>
	</form>
	</div> <!-- end div right_side -->
</div><!-- end div center_me -->

<h2 class="error_msg">
<%
    if(null!=request.getAttribute("errorMsg"))
    {
        out.println(request.getAttribute("errorMsg"));
    }
%>
</h2>

</body>
</html>