<%@page import="java.time.Year"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pocket Calculator</title>
<link rel="icon" href="icons/calculator-svgrepo-com.svg">
</head>
<body bgcolor="#cce6ff" leftmargin="0" topmargin="0" bottommargin="0" text="#4d4d00">
	<table align="center" bgcolor="#d6d6c2" width="100%">
		<tr>
			<td align="right" colspan="2">
				<h1>Best Pocket Calculator</h1>
			</td>
			<td align="center">
				<img hspace="10" vspace="50" width="170" src="icons/calculator-svgrepo-com.svg" alt="calculator">
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<br><br>
				<hr>
			</td>
		</tr>
		<tr>
			<td align="center" width="33%">
				<h3>This app was created using JAVA <img width="25" src="icons/java-svgrepo-com.svg" alt="java"></h3>
			</td>
			<td align="center" width="33%">
				<h3>It runs on any OS as long as you have JVM installed <img width="25" src="icons/laptop-java-svgrepo-com.svg" alt="jvm"></h3>
			</td>
			<td align="center">
				<h3>It has complex math/scientific functionality <img width="30" src="icons/square-root-of-x-svgrepo-com.svg" alt="square"></h3>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<hr>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br><br>
				<p align="right">You can buy it (download) <a href="<%=request.getContextPath()%>/signup.jsp"><button><b>HERE</b></button></a></p>
			</td>
			<td></td>
		</tr>
		<tr>
			<td colspan="3">
				<br>
				<hr>
				<p align="right"><small>Copyright Pocket Inc &copy; <%=Year.now()%></small></p>
			</td>
		</tr>
	
	</table>
</body>
</html>