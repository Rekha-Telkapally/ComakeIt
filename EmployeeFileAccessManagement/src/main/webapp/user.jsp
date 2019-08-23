<%@page import="UserFunctionalities.Userfunctionalities"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="#F08080" align="center">
	<form action="Userfunctionalities" method="post">
		<h1>
			<u>USER LOGIN PAGE</u>
		</h1>
		<label>Change Password:</label> <input type="text" name="password">
		<input type="submit" value="new password" name="button1"><br>
		<br> <label>Address:</label> <input type="text" name="address">
		<input type="submit" value="change address" name="button2"><br>
		<br> <label>GO BACK TO LOGIN PAGE:</label> <input type="submit"
			value="Home page" name="button3">
	</form>
</body>
</html>