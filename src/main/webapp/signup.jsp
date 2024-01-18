<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CREATE AN ACCOUNT</title>
<link rel="icon" href="icons/calculator-svgrepo-com.svg">
</head>
<body bgcolor="#cce6ff" leftmargin="0" topmargin="0" bottommargin="0" text="#4d4d00">
	<table align="center" bgcolor="#d6d6c2" width="40%">
		<tr>
			<td align="center">
				<br>
				<h1>CREATE AN ACCOUNT</h1>
				<br><br>
			</td>
		</tr>
		<tr>
			<td align="center">
				<hr>
				<h2>To download our app you need an account!</h2>
				<hr><br><br>
			</td>
		</tr>
		<tr>
			<td align="center">
				<form method="post" action="<%=request.getContextPath()%>/signup-action">
					<label for="fullName">Full Name</label>
					<br>
					<input name="fullName" type="text" required/>
					<br><br>
					<label for="phoneNumber">Phone Number</label>
					<br>
					<input name="phoneNumber" type="tel" required/>
					<br><br>
					<label for="emailAddress">Email Address</label>
					<br>
					<input name="emailAddress" type="email" required/>
					<br><br>
					<label for="password">Password</label>
					<br>
					<input name="password" type="password" required/>
					<br><br>
					<label for="passwordConfirm">Password Confirm</label>
					<br>
					<input name="passwordConfirm" type="password" required/>
					<br><br><br><hr><br>
					<button>SIGN UP</button>
					<br><br><hr><br><br><br><br>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>