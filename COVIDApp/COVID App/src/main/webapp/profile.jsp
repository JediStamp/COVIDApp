<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>COVID App</title>
<link rel="stylesheet" href="http://localhost:8080/COVID_App/html/css/style.css">
</head>

<body>
<h1 class="centered">Profile Page</h1>

	<!--  Navigation bar  -->
	<div class="menubar">
		<a id="selected" href="profile.jsp">PROFILE</a>
		<a href="questionnaire.jsp">QUESTIONNAIRE</a>
		<a href="results.jsp">RESULTS</a>
		<a class="right_align" href="index.jsp">LOGOUT</a>
	</div>


<div class="center-me">
	<div class="light left_side">
	<form action="./ProfileServlet" method="post">
	<table>
		<tr>
			<th colspan=2>Profile</th>
		</tr>
		
		<tr>
			<td><label for="first_name">First Name:</label></td>
			<td><input type="text" id="first_name" name="first_name" size="50"
			value=<% out.println(request.getSession().getAttribute("firstName"));%>/></td>
		</tr>
		
		<tr>
			<td><label for="last_name">Last Name:</label></td>
			<td><input type="text" id="last_name" name="last_name" size="50"
			value=<% out.println(request.getSession().getAttribute("lastName"));%>/></td>
		</tr>
		
		<tr>
			<td><label for="email">Email:</label></td>
			<td><input type="email" id="email" name="email" size="50"
			value=<% out.println(request.getSession().getAttribute("email"));%>/></td>
		</tr>
		
		<tr>
			<td>Change Password</td>
			<td>?</td>
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
			<th><img src="http://localhost:8080/COVID_App/html/images/profile.jpg" width="200px"></th>
		</tr>
		
		<tr>
			<td><label for="owner">Team Owner</label></td>
			<td><input type="checkbox" id="owner" name="owner"/></td>
		</tr>
	</table>
	</form>
	</div> <!-- end div right_side -->
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
