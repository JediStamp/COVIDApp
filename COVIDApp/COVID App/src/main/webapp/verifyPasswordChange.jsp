<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>COVID App</title>
<link rel="stylesheet" href="http://localhost:8080/COVID_App/html/css/style.css">
</head>

<body>
<h1 class="centered">User Verification</h1>
<hr>

<div class="center-me">
	<div class="dark left_side">
	<table>
		<tr>
			<th>About the COVID App</th>
		</tr>
		
		<tr>
			<td>This is our introductory blurb about our app. Much text goes here.</td>
		</tr>
		
	</table>
	</div> <!-- end div dark left_side -->
	
	<div class="light right_side">
	<form action="./changePass.jsp" method="post">
	<table>
		<tr>
			<th colspan=7>Finish Registering</th>
		</tr>
		
		<tr>
			<td>Verification Code:</td>
			<td><input class="single" type="text" id="v1" name="v1" maxlength="1" 
				required="required"/></td>
			<td><input class="single" type="text" id="v2" name="v2" maxlength="1"
				required="required"/></td>
			<td><input class="single" type="text" id="v3" name="v3" maxlength="1"
				required="required"/></td>
			<td><input class="single" type="text" id="v4" name="v4" maxlength="1"
				required="required"/></td>
			<td><input class="single" type="text" id="v5" name="v5" maxlength="1"
				required="required"/></td>
			<td><input class="single" type="text" id="v6" name="v6" maxlength="1"
				required="required"/></td>
		</tr>
		
		<tr>
			<td class="centered" colspan=7><input type="submit" value="Change Password"></td>
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