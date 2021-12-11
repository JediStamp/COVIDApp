<!DOCTYPE html>
<html>
<%@ page import="java.util.ArrayList" %>
<%@ page import="questionnaires.Result" %>
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

 <% if (null!=request.getAttribute("resultsOut"))
{
    %>
    <p id="table-here"></p>
    <table class="resultsTable">
    <tr>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Time Answered</th>
    <th>Clear</th>
    </tr>	
	
	<% 
	ArrayList<Result> results = (ArrayList<Result>)request.getAttribute("resultsOut");
	for (int i = 0; i < results.size(); i++){
		%>
		<tr>
			<td><%=results.get(i).getFirstName()%></td>
			<td><%=results.get(i).getLastName()%></td>
			<td><%=results.get(i).getTimeAnswered()%></td>
			<%
			if (results.get(i).getGoodToGo().equals("Yes")){
			%>
			<td style="background-color:#BA3B54;">
			<%
			} else {
			%>
			<td style="background-color:#6AF190;">
			<% 
			} 
			%>
			<%=results.get(i).getGoodToGo()%></td>
		</tr>
	<%
	}
	%>
	</table>	

	<% }
	%>
</div>

</body>
</html>