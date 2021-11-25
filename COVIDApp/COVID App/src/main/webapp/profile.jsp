<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>COVID App</title>
<link rel="stylesheet" href="http://localhost:8080/COVID_App/html/css/style.css">
</head>

<body>
<h1 class="centered">Profile Page</h1>

<%@ include file="header.jsp" %> 

<%
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
			<td colspan=2 class="right_align">
			<input type="submit" value="Create Team" formaction="team.jsp"/>
			<input type="submit" value="Update Profile">
			<input type="submit" value="Delete Profile" formaction="./DeleteServlet"/>
			</td>
		</tr>
		
	</table>
	</form>
	</div> <!-- end div dark left_side -->
	
	<div class="dark right_side">
	<form action="./UploadServlet" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<th><img src=
			<%
			String vaxPath = "C:/VaxPassport/passport.jpg";
			java.io.File img = new java.io.File(vaxPath);
			if (img.exists()){
				out.println(vaxPath);
			} 
			else {
				out.println("http://localhost:8080/COVID_App/html/images/profile.jpg");
			}
			%>
			 width="200px"/></th>
		</tr>

		<tr>
			<td class="centered"><input type="file" name="file" /></td>
		</tr>
			
		<tr>
			<td class="centered"><input type="submit" value="Upload" /></td>
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
