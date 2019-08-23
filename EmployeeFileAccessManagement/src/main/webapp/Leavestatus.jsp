<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="#00ffff" align="center">

	<h1>YOUR LEAVE STATUS:</h1>
	<a href="admin.jsp"><h2><----------GoBack</h2></a>
	<%
		int i = (int) request.getAttribute("list");
		if (i == 1) {
			out.println("Accepted.......!");
		} else if (i == 0) {
			out.println("Pending.........");
		} else
			out.println("Rejected(X)");
	%>
</body>
</html>