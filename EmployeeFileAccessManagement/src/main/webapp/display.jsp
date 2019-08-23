<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="#00ffff">
	<form action="admin.jsp">
		<table>
			<h2>LIST OF USERS:</h2>
			<%
				List<String> users = (List<String>) request.getAttribute("list");
				Iterator i = users.iterator();
				while (i.hasNext()) {
			%><tr>
				<td><%=i.next()%></td>
			</tr>
			<%
				}
			%>

		</table>
		<input type="submit" value="back to page" name="button">
	</form>
</body>
</html>