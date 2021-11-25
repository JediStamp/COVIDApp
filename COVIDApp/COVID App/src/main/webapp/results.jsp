<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>COVID App</title>
<link rel="stylesheet" href="http://localhost:8080/COVID_App/html/css/style.css">
</head>

<body>
<h1 class="centered">Results Page</h1>

<%@ include file="header.jsp" %> 

	<div>
	<form action="./ResultsServlet" method="post">
		<table>
			<tr>
				<td class="centered" colspan=2><input type="submit" value="Show me the results" onclick="clearcontent('table-here')"></td>
			</tr>
		</table>
	</form>
	</div>

<div class="center-me">

 <% if (null!=request.getAttribute("lineOut"))
{
    %>	
	<p id="table-here">
	<%
	out.println(request.getAttribute("lineOut"));
	%>
	</p>	
	<% }
	%>
</div>

</body>
</html>