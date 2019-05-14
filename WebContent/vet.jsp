<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>VETS</title>
</head>
<body>
	<a href="VetServlet?method=showVets">click</a>
	<table>
		<c:forEach items="${list}" var="item">
			<tr>
				<td>${item.id }</td>
				<td>${item.name }</td>
			</tr>
		</c:forEach>
	</table>
	<font color="green" size="22">
	<%
		out.print(request.getParameter("message")+"<br>");
	%>
	</font>
</body>
</html>