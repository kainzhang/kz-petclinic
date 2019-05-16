<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>OWNER</title>
</head>

<body>
	<%@ include file="header.jsp"%>
	<div id=owner-message>
		<font color="green" size="18">
		<%
		if(request.getAttribute("message")!= null)
			out.print(request.getAttribute("message")+"<br>");
		%>
		</font>
	</div>
	<div id="owner-search">
		<form action="OwnerServlet?method=searchOwner" method="post">
			<label>关键字：</label>
			<input type="text" name="keyword" value=${param.keyword} >
			<input type="submit" value="查询">
		</form>
	</div>
	<div id="owner-insert">
		<form action="OwnerServlet?method=insertOwner" method="post">
			<label>新顾客：</label>
			名字<input type="text" name="name">
			电话<input type="text" name="tel">
			住址<input type="text" name="addr">
			<input type="submit" value="添加">
		</form>
	</div>
	<div id="owner-show">
		<table border="1">
			<c:forEach items="${list}" var="item">
				<tr>
					<td>${item.id }</td>
					<td>${item.name }</td>
					<td>${item.tel }</td>
					<td>${item.addr }</td>
					<td><a href="OwnerServlet?method=updateOwner&id=${item.id}">修改</a></td>
					<td><a href="OwnerServlet?method=deleteOwner&id=${item.id}">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<%@ include file="footer.jsp" %>
</body>
</html>