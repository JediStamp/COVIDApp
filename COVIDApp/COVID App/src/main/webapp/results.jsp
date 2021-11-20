<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>COVID App</title>
<link rel="stylesheet" href="http://localhost:8080/COVID_App/html/css/style.css">
</head>

<body>
<h1 class="centered">Results Page</h1>

	<!--  Navigation bar  -->
	<div class="menubar">
		<a href="profile.jsp">PROFILE</a>
		<a href="questionnaire.jsp">QUESTIONNAIRE</a>
		<a id="selected" href="results.jsp">RESULTS</a>
		<a class="right_align" href="LogoutServlet?logout=true">LOGOUT</a>
	</div>

    <script>
        function clearcontent(elementID) {
            document.getElementById(elementID).innerHTML = "";
        }
    </script>

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