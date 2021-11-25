<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>COVID App</title>
<link rel="stylesheet" href="http://localhost:8080/COVID_App/html/css/style.css">
</head>

<body>
<h1 class="centered">Question Page</h1>

<%@ include file="header.jsp" %> 
	
<div class="center-me">

	<div class="light">
	<form action="./QuestionServlet" method="post">
	<table>
	<tr>
	<td><label for="question_content">Question Content:</label></td>
	<td colspan="3"><input type="text" id="question_content" name="question_content" size="30"/></td>
	</tr>

	<tr>
	<td><label for="correct_ans">Question Answer:</label></td>
	<td><input type="radio" name="correct_ans" value="Yes" required="required"></td>
	<td><input type="radio" name="correct_ans" value="No" required="required"></td>
	<td><input type="submit" value="Add"></td>
	</tr>
	
	</table>
	</form>

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
