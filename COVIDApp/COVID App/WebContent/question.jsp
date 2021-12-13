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
	<td rowspan="5"><label for="question_content">Question Content:</label></td>
	<td colspan="3" rowspan="5"><textarea cols="50" rows="5" id="question_content" name="question_content"></textarea></td>
	</tr>

	<tr>
	<td><label for="correct_ans">Question Answer:</label></td>
	<td><label for="correct_ans">Yes</label><input type="radio" name="correct_ans" value="Yes" required="required"></td>
	<td><label for="correct_ans">No</label><input type="radio" name="correct_ans" value="No" required="required"></td>
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
