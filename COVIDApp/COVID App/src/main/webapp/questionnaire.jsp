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
	
	<!--  Navigation bar  -->
	<div class="menubar">
		<a href="profile.jsp">PROFILE</a>
		<a id="selected" href="questionnaire.jsp">QUESTIONNAIRE</a>
		<a href="results.jsp">RESULTS</a>
		<a class="right_align" href="index.jsp">LOGOUT</a>
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
			<tr><!-- Q1 -->
				<td>I am fully vaccinated against COVID-19.</td>
				<td colspan=2 style="text-align: center; vertical-align: middle;">
				<input type="radio" name="select" value="Yes"> &emsp;&emsp;&emsp; 
				<input type="radio" name="select" value="No">
				</td>
			</tr>
			
			<tr><!-- Q2 -->
				<td>In the last 10 days, have you been identified as a close contact<br/>
					of someone who currently has COVID-19?</td>
				<td colspan=2 style="text-align: center; vertical-align: middle;">
				<input type="radio" name="select2" value="Yes"> &emsp;&emsp;&emsp; 
				<input type="radio" name="select2" value="No">
				</td>
			</tr>
			
			<tr><!-- Q3 -->
				<td>In the last 10 days, have you received a COVID Alert exposure <br/>
					notification on your cell phone?</td>
				<td colspan=2 style="text-align: center; vertical-align: middle;">
				<input type="radio" name="select3" value="Yes"> &emsp;&emsp;&emsp; 
				<input type="radio" name="select3" value="No">
				</td>
			</tr>
			
			<tr><!-- Q4 -->
				<td>In the last 14 days, have you travelled outside of Canada?</td>
				<td colspan=2 style="text-align: center; vertical-align: middle;">
				<input type="radio" name="select4" value="Yes"> &emsp;&emsp;&emsp; 
				<input type="radio" name="select4" value="No">
				</td>
			</tr>

			<tr><!-- Q5 -->
				<td> Are you currently experiencing fever and/or Chills? <br/>
					<i>Temperature of 37.8 degrees Celcius/100 degrees Fahrenheit or more</i></td>
				<td colspan=2 style="text-align: center; vertical-align: middle;">
				<input type="radio" name="select5" value="Yes"> &emsp;&emsp;&emsp; 
				<input type="radio" name="select5" value="No">
				</td>
			</tr>
			
			<tr><!-- Q6 -->
				<td>Are you currently experiencing cough or barking cough? <br/>
					<i>Continuous, more than usual, making a whistling noise when breathing <br/>
					(not related to asthma, post-infectious reactive airways, COPD, or <br/>
					other known causes or conditions you already have)</i></td>
				<td colspan=2 style="text-align: center; vertical-align: middle;">
				<input type="radio" name="select6" value="Yes"> &emsp;&emsp;&emsp; 
				<input type="radio" name="select6" value="No">
				</td>
			</tr>
			
			<tr><!-- Q7 -->
				<td>Are you currently experiencing shortness of breath?<br/>
					<i>Out of breath, unable to breathe deeply (not related to asthma <br/> 
					or other known causes or conditions you already have)</i></td>
				<td colspan=2 style="text-align: center; vertical-align: middle;">
				<input type="radio" name="select7" value="Yes"> &emsp;&emsp;&emsp; 
				<input type="radio" name="select7" value="no">
				</td>
			</tr>
			
			<tr><!-- Q8 -->
				<td>Are you currently experiencing decrease in loss of taste or breath?<br/>
					<i>Not related to seasonal allergies, neurological disorders, or other <br/>
					known causes or conditions you already have</i></td>
				<td colspan=2 style="text-align: center; vertical-align: middle;">
				<input type="radio" name="select8" value="Yes"> &emsp;&emsp;&emsp; 
				<input type="radio" name="select8" value="No"></td>
			</tr>
			<tr><!-- Q9 -->
				<td>Are you currently experiencing muscle aches/joint pain?<br/>
					<i>Unusual, long-lasting (not related to getting a COVID-19 vaccine in the last 48 hours,<br/>
					a sudden injury, fibromyalgia, or other known causes or conditions you already have)</i></td>
				<td colspan=2 style="text-align: center; vertical-align: middle;">
				<input type="radio" name="select9" value="Yes"> &emsp;&emsp;&emsp; 
				<input type="radio" name="select9" value="No"></td>
			</tr>
			<tr>
				<td>Are you currently experiencing extreme tiredness?<br/>
					<i>Unusual, fatigue, lack of energy (not related to getting a COVID-19 vaccine in the last 48 hours, <br/> 
					depression, insomnia, thyroid dysfunction, or other known causes or conditions you already have)</i></td>
				<td colspan=2 style="text-align: center; vertical-align: middle;">
				<input type="radio" name="select0" value="Yes"> &emsp;&emsp;&emsp; 
				<input type="radio" name="select0" value="No">
				</td>
			</tr>
			<!-- <tr>
				<td><label for="checks">None of the above</label></td>
				<td colspan="2" style="text-align: center; vertical-align: middle;"><input type="checkbox" value="Yes" name="checks"/></td>
			</tr> -->
			
			<tr>
				<td colspan="3" style="text-align: center; vertical-align: middle;"><input type="submit" value="Submit Questionnaire"/></td>
		</tbody>		
	</table>
	</form>
	</div>
</body>
</html>