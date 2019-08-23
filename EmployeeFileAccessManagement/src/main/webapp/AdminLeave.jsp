<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body align="center" bgcolor="#F08080">
	<form action="Adminfunctionalities" method="post">
		<h2>
			<u>LEAVE FORM</u>
		</h2>
		<label>Enter Id:</label> <input type="text" name="id"><br>
		<br> <label>Reason for Applying:</label> <select name="dropdown">
			<option value="sick leave" selected>sick leave</option>
			<option value="casual leave">casual leave</option>
			<option value="outoff station">outoff station</option>
			<option value="other Reason">Other Reason</option>
		</select><br>
		<br> <label>Start Date:</label> <input type="date" id="start"
			name="start_date"><br>
		<br> <label>Number of Days:</label> <input type="text" name="days"><br>
		<br> <input type="submit" value="Apply" name="button8">


	</form>
</body>
</html>