<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"
	import="ManagerFunctionalities.Leavebean"
	import="ManagerFunctionalities.Managerfunctionalities"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="background-color: #F08080">

	<table border="3" style="border: 2px solid black; width: 100%">
		<form action="Managerfunctionalities" method="post">
			<h2>
				<%
					out.println("List of Leaves applyed:");
					List<Leavebean> list = (List<Leavebean>) request.getAttribute("list");
				%>
			</h2>
			<tr>
				<th>ID</th>
				<th>REASON</th>
				<th>START DATE</th>
				<th>NUMBER OF DAYS</th>
				<th>STATUS</th>
				<th>GRANT YES</th>
				<th>GRANT NO</th>
			</tr>
			<%
				for (Leavebean b : list) {
			%>
			<tr>
				<td><%=b.getId()%></td>
				<td><%=b.getReason()%></td>
				<td><%=b.getStartdate()%></td>
				<td><%=b.getDays()%></td>
				<td><%=b.getStatus()%></td>
				<td><a href=Grantedleave?Id=
					<%out.print(b.getId());%>&status=accept>>accept</a></td>
				<td><a href=Grantedleave?Id=
					<%out.print(b.getId());%>&status=reject>>reject</a></td>
				</td>
			</tr>
			<%
				}
			%>
			<input type="submit" value="Go back" name="button">
		</form>
	</table>
</body>
</html>