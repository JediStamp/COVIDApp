<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>COVID App</title>
<link rel="stylesheet" href="http://localhost:8080/COVID_App/html/css/style.css">
</head>

<body>
<h1 class="centered">Home Page</h1>
<hr>

<div class="center-me">
	<div class="dark left_side">
	<form action="./LoginServlet" method="get">
	<table>
		<tr>
			<th>About the COVID App</th>
		</tr>
		
		<tr>
			<td>This is our introductory blurb about our app. Much text goes here.</td>
		</tr>

		<tr>
			<td class="centered">Already have an account?</td>
		</tr>
		
		<tr>
			<td class="centered"><input type="submit" value="Sign In"></td>
		</tr>
		
	</table>
</form>
	</div> <!-- end div dark left_side -->
	
	<div class="light right_side">
	<form action="./RegisterServlet" method="post">
	<table>
		<tr>
			<th colspan=2>Register</th>
		</tr>
		
		<tr>
			<td><label for="first_name">First Name:</label></td>
			<td><input type="text" id="first_name" name="first_name" size="50"
				required="required"/></td>
		</tr>
		
		<tr>
			<td><label for="last_name">Last Name:</label></td>
			<td><input type="text" id="last_name" name="last_name" size="50" 
				required="required"/></td>
		</tr>
		
		<tr>
			<td><label for="email">Email:</label></td>
			<td><input type="email" id="email" name="email" size="50" 
				required="required"/></td>
		</tr>
		
		<tr>
			<td><label for="pwd">Password:</label></td>
			<td><input type="password" id="pwd" name="pwd" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" size="50"
			title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
				onChange="onChange()" required="required"/></td>
		</tr>
		
		<tr>
			<td><label for="conf_pwd">Confirm Password:</label></td>
			<td><input type="password" id="conf_pwd" name="conf_pwd" onChange="onChange()" size="50"
				required="required"/></td>
		</tr>
		
		<tr>
			<td class="centered" colspan=2><input type="submit" value="Register"></td>
		</tr>
		
	</table>
</form>
	</div> <!-- end div right_side -->
</div><!-- end div center_me -->

<div id="message">
  <h3>Password must contain the following:</h3>
  <p id="letter" class="invalid">A <b>lowercase</b> letter</p>
  <p id="capital" class="invalid">A <b>capital (uppercase)</b> letter</p>
  <p id="number" class="invalid">A <b>number</b></p>
  <p id="length" class="invalid">Minimum <b>8 characters</b></p>
</div>

<script>
var myPwd = document.getElementById("pwd");
var pwdConf = document.getElementById("conf_pwd");
var letter = document.getElementById("letter");
var capital = document.getElementById("capital");
var number = document.getElementById("number");
var length = document.getElementById("length");

// When the user clicks on the password field, show the message box
myPwd.onfocus = function() {
  document.getElementById("message").style.display = "block";
}

// When the user clicks outside of the password field, hide the message box
myPwd.onblur = function() {
  document.getElementById("message").style.display = "none";
}

// When the user starts to type something inside the password field
myPwd.onkeyup = function() {
	
  // Validate lowercase letters
  var lowerCaseLetters = /[a-z]/g;
  if(myPwd.value.match(lowerCaseLetters)) {  
    letter.classList.remove("invalid");
    letter.classList.add("valid");
  } else {
    letter.classList.remove("valid");
    letter.classList.add("invalid");
  }
  
  // Validate capital letters
  var upperCaseLetters = /[A-Z]/g;
  if(myPwd.value.match(upperCaseLetters)) {  
    capital.classList.remove("invalid");
    capital.classList.add("valid");
  } else {
    capital.classList.remove("valid");
    capital.classList.add("invalid");
  }

  // Validate numbers
  var numbers = /[0-9]/g;
  if(myPwd.value.match(numbers)) {  
    number.classList.remove("invalid");
    number.classList.add("valid");
  } else {
    number.classList.remove("valid");
    number.classList.add("invalid");
  }
  
  // Validate length
  if(myPwd.value.length >= 8) {
    length.classList.remove("invalid");
    length.classList.add("valid");
  } else {
    length.classList.remove("valid");
    length.classList.add("invalid");
  }
 
}
//Validate pwd === conf_pwd
function onChange() {
	  const password = document.querySelector('input[name=pwd]');
	  const confirm = document.querySelector('input[name=conf_pwd]');
	  if (confirm.value === password.value) {
	    confirm.setCustomValidity('');
	  } else {
	    confirm.setCustomValidity('Passwords do not match');
	  }
	}
</script>

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