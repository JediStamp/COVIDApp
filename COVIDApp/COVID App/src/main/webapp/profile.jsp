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
		<a href="team.jsp">TEAM</a>
		<a href="question.jsp">QUESTION</a>
		<a id="selected" href="results.jsp">RESULTS</a>
		<a class="right_align" href="LogoutServlet?logout=true">LOGOUT</a>
	</div>
<%@ page import ="app.User"   %>
<%
User user = (User) request.getSession().getAttribute("thisUser"); 
			String first_name = user.getFirstName();
			String last_name = user.getLastName();
			String email = user.getEmail();
%>
<div class="center-me">
	<div class="light left_side">
	<form action="./ProfileServlet" method="post">
	<table>
		<tr>
			<th colspan=2>Profile</th>
		</tr>
		
		<tr>
			<td><label class="profile-label" for="first_name">First Name:</label></td>
			<td><input class="profile" type="text" id="first_name" name="first_name" size="30"
			value=<% out.println(user.getFirstName());%>/></td>
		</tr>
		
		<tr>
			<td><label class="profile-label" for="last_name">Last Name:</label></td>
			<td><input class="profile" type="text" id="last_name" name="last_name" size="30"
			value=<% out.println(user.getLastName());%>/></td>
		</tr>
		
		<tr>
			<td><label class="profile-label">Email:</label></td>
			<td><label class="profile-label"><% out.println(user.getEmail());%></label></td>
		</tr>
		
		<tr>
			<td colspan=2 class="right_align"><a href="changePass.jsp">Change My Password</a></td>
		</tr>
		
		<tr>
			<td colspan=2 class="right_align"><input type="submit" value="Update">
			<input type="submit" value="Delete" formaction="./DeleteServlet"></td>
		</tr>
		
	</table>
	</form>
	</div> <!-- end div dark left_side -->
	
	<div class="dark right_side">
	<form action="./ProfileServlet" method="get">
	<table>
		<tr>
			<th colspan=2><img src="http://localhost:8080/COVID_App/html/images/profile.jpg" width="200px"></th>
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
