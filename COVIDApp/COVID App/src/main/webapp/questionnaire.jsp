<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Questionnaire</title>
<link rel="stylesheet" href="http://localhost:8080/COVID_App/html/css/style.css">
</head>
<body>
	<h1 class="centered"> Fill In Questionnaire</h1>
	
	<!--  This is where the code for navigation bar will go  -->
	<div class="menubar">
		<a href="profile.jsp">PROFILE</a>
		<a href="questionnaire.jsp">QUESTIONNAIRE</a>
		<a href="">RESULTS</a>
	</div>
	<!-- Creating a Table to store the questions and "Yes" & "No" buttons -->
	<div class="center-me">
	<form action="./QuestionnaireServlet" method="post">
	<table class="questionTable">
		<thead>
			<tr>
				<th> Questions </th>
				<th> Yes </th>
				<th> No </th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>I am fully vaccinated against COVID-19.</td>
				<td style="text-align: center; vertical-align: middle;"><input type="checkbox" id="yes1" name="yes1"/></td>
				<td style="text-align: center; vertical-align: middle;"><input type="checkbox" id="no1" name="no1"/></td>
			</tr>
			<tr>
				<td>In the last 10 days, have you been identified as a close contact <br/>
					of someone who currently has COVID-19?</td>
				<td style="text-align: center; vertical-align: middle;"><input type="checkbox" id="yes2" name="yes2"/></td>
				<td style="text-align: center; vertical-align: middle;"><input type="checkbox" id="no2" name="no2"/></td>
			</tr>
			<tr>
				<td>In the last 10 days, have you received a COVID Alert exposure <br/>
					notification on your cell phone?</td>
				<td style="text-align: center; vertical-align: middle;"><input type="checkbox" id="yes3" name="yes3"/></td>
				<td style="text-align: center; vertical-align: middle;"><input type="checkbox" id="no3" name="no3"/></td>
			</tr>
			<tr>
				<td>In the last 14 days, have you travelled outside of Canada?</td>
				<td style="text-align: center; vertical-align: middle;"><input type="checkbox" id="yes4" name="yes4"/></td>
				<td style="text-align: center; vertical-align: middle;"><input type="checkbox" id="no4" name="no4"/></td>
			</tr>
			<tr>
				<td>Are you currently experiencing any of these symptoms? <br/>
					Choose any/all that are new, worsening or not related to any other <br/>
					health conditions you already have:</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>Fever and/or Chills <br/>
					<i>Temperature of 37.8 degrees Celcius/100 degrees Fahrenheit or more</i></td>
				<td colspan="2" style="text-align: center; vertical-align: middle;"><input type="checkbox" id="check1" name="check1"/></td>
			</tr>
			<tr>
				<td>Cough or barking cough <br/>
					<i>Continuous, more than usual, making a whistling noise when breathing <br/>
					(not related to asthma, post-infectious reactive airways, COPD, or <br/>
					other known causes or conditions you already have)</i></td>
				<td colspan="2" style="text-align: center; vertical-align: middle;"><input type="checkbox" id="check2" name="check2"/></td>
			</tr>
			<tr>
				<td>Shortness of breath<br/>
					<i>Out of breath, unable to breathe deeply (not related to asthma <br/> 
					or other known causes or conditions you already have)</i></td>
				<td colspan="2" style="text-align: center; vertical-align: middle;"><input type="checkbox" id="check3" name="check3"/></td>
			</tr>
			<tr>
				<td>Decrease in loss of taste or breath<br/>
					<i>Not related to seasonal allergies, neurological disorders, or other <br/>
					known causes or conditions you already have</i></td>
				<td colspan="2" style="text-align: center; vertical-align: middle;"><input type="checkbox" id="check4" name="check4"/></td>
			</tr>
			<tr>
				<td>Muscle aches/joint pain<br/>
					<i>Unusual, long-lasting (not related to getting a COVID-19 vaccine in the last 48 hours,<br/>
					a sudden injury, fibromyalgia, or other known causes or conditions you already have)</i></td>
				<td colspan="2" style="text-align: center; vertical-align: middle;"><input type="checkbox" id="check5" name="check5"/></td>
			</tr>
			<tr>
				<td>Extreme tiredness<br/>
					<i>Unusual, fatigue, lack of energy (not related to getting a COVID-19 vaccine in the last 48 hours, <br/> 
					depression, insomnia, thyroid dysfunction, or other known causes or conditions you already have)</i></td>
				<td colspan="2" style="text-align: center; vertical-align: middle;"><input type="checkbox" id="check6" name="check6"/></td>
			</tr>
			<tr>
				<td>None of the above</td>
				<td colspan="2" style="text-align: center; vertical-align: middle;"><input type="checkbox" id="check7" name="check7"/></td>
			</tr>
		</tbody>		
	</table>
	</form>
	</div>
</body>
</html>