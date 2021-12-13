<!--  Navigation bar  -->
<%@ page import ="beans.app.User"   %>
<%
	User user = (User) request.getSession().getAttribute("thisUser"); 
%>
	
<div class="menubar">
	<a href="profile.jsp">PROFILE</a>
	<a href="questionnaire.jsp">QUESTIONNAIRE</a>
	<% out.println(user.getUserRole().seeTeamPage()); %>
	<% out.println(user.getUserRole().seeQuestionPage()); %>
	<% out.println(user.getUserRole().seeResultsPage()); %>
	<a class="right_align" href="LogoutServlet?logout=true">LOGOUT</a>
</div>