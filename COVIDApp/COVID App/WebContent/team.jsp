<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>COVID App</title>
<link rel="stylesheet" href="http://localhost:8080/COVID_App/html/css/style.css">
</head>

<body>
<h1 class="centered">Team Page</h1>

<%@ include file="header.jsp" %> 
	
<div class="center-me">

	<div class="light">
	<form action="./TeamServlet" method="get">
	<table>
	<tr>
	<td><label for="team_name">Team Name:</label></td>
	<td><input type="text" id="team_name" name="team_name" size="30"/></td>
	<td><input type="submit" value="Edit"></td>
	</tr>
	</table>
	</form>

	<div class="dark ">
	<form action="./TeamServlet" method="post">
	<table>

		<tr>
			<td><label for="member_email">Add Team Member:</label></td>
			<td><input type="email" id="member_email" name="member_email" size="30" placeholder="e-mail"/></td>
			<td><label for="role">Role:</label></td>
			<td>
			<select id="role" name="role" size="1">
			<option value="owner">Owner</option>
			<option value="admin">Administrator</option>
			<option value="user">User</option>
			</select>
			</td>
			<td><input type="submit" value="Add"></td>
		</tr>
		
	</table>
	</form>
	</div> <!-- end of dark div -->	
	
	<table>
	<tr><th>Team Members</th><th>Email</th><th>Role</th></tr>
	</table>
	
	</div><!-- End of light div -->
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
